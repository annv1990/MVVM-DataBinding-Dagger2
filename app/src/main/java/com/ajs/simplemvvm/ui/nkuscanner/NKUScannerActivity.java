package com.ajs.simplemvvm.ui.nkuscanner;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ajs.simplemvvm.BR;
import com.ajs.simplemvvm.MVVMViewModelProviderFactory;
import com.ajs.simplemvvm.R;
import com.ajs.simplemvvm.base.BaseActivity;
import com.ajs.simplemvvm.databinding.ActivityNkuScannerBinding;
import com.ajs.simplemvvm.ui.nkuscanner.zxing.camera.CameraManager;
import com.ajs.simplemvvm.ui.nkuscanner.zxing.decoding.CaptureActivityHandler;
import com.ajs.simplemvvm.ui.nkuscanner.zxing.decoding.InactivityTimer;
import com.ajs.simplemvvm.ui.nkuscanner.zxing.view.ViewfinderView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.inject.Inject;

public class NKUScannerActivity extends BaseActivity<ActivityNkuScannerBinding, NKUScannerActivityViewModel>
        implements SurfaceHolder.Callback {

    ActivityNkuScannerBinding mBinding;
    NKUScannerActivityViewModel mViewModel;

    @Inject
    MVVMViewModelProviderFactory mFactory;

    TextView tvCardText;
    Button btStartScan;

    private InactivityTimer inactivityTimer;
    private MediaPlayer mediaPlayer;
    private CaptureActivityHandler handler;
    private Vector<BarcodeFormat> decodeFormats;
    private static final float BEEP_VOLUME = 0.10f;
    private String characterSet;
    private boolean vibrate;
    private boolean playBeep;
    private boolean hasSurface;
    private int type = -1;//1、send order   2、buy sukipass
    private boolean isErrorInStore = false;


    ViewfinderView viewfinderView;
    TextView tvScanAlert;
    SurfaceView surfaceView;
    ImageView testImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindView();
        initWidget();
    }

    private void bindView() {
        viewfinderView = findViewById(R.id.viewfinderView);
        tvScanAlert = findViewById(R.id.tvScanAlert);
        surfaceView = findViewById(R.id.preview_view);
        testImage = findViewById(R.id.testImage);
    }

    protected void initWidget() {
//        super.initWidget();
        type = getIntent().getIntExtra("type", -1);
//        Logger.d("-----------type------------"+type);
        if (type == 1) {
            tvScanAlert.setText("QRコードを読み取ると\\nお支払いが実行されます");
        } else if (type == 2) {
            tvScanAlert.setText("QRコードを読み取ると登録されます");
        }
        CameraManager.init(getApplication());
        hasSurface = false;
        inactivityTimer = new InactivityTimer(this);

        //debug test
//        handleDecode(new Result("Z7711Z A 1Z-",null,null,null), null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //isRightCode("Z7711Z C33Z5");
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if (hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        decodeFormats = null;
        characterSet = null;

        playBeep = true;
        AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        initBeepSound();
        vibrate = true;
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        super.onDestroy();
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
        } catch (IOException ioe) {
            return;
        } catch (RuntimeException e) {
            Dialog dialog = new AlertDialog.Builder(this).setMessage("カメラを取得できません、カメラへのアクセスを許可するかを確認してください。")
                    .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setResult(RESULT_CANCELED);
                            finish();
                        }
                    }).create();
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            return;
        }
        if (handler == null) {
            handler = new CaptureActivityHandler(this, decodeFormats, characterSet);
        }
    }

    private void initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            //mediaPlayer=MediaPlayer.create(this,R.raw.beep);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(beepListener);

            AssetFileDescriptor file = getResources().openRawResourceFd(
                    R.raw.beep);
            try {
                mediaPlayer.setDataSource(file.getFileDescriptor(),
                        file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mediaPlayer.prepare();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        }
    }

    private final MediaPlayer.OnCompletionListener beepListener = new MediaPlayer.OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };

    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    @Override
    protected int getResourceLayoutId() {
        return R.layout.activity_nku_scanner;
    }

    @Override
    public int getBindingVariable() {
        return BR.scannerActivityVM;
    }

    @Override
    public NKUScannerActivityViewModel getViewModel() {
        return mViewModel = ViewModelProviders.of(this, mFactory).get(NKUScannerActivityViewModel.class);
    }

    @Override
    protected void startLoading() {
        super.startLoading();
    }

    @Override
    protected void stopLoading() {
        super.stopLoading();
    }

    @Override
    public void performDependencyInjection() {
        super.performDependencyInjection();
    }

    private void startQRScanner() {
        new IntentIntegrator(this).initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                updateText(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void updateText(String scanCode) {
        tvCardText.setText(scanCode);
    }


    public Handler getHandler() {
        return handler;
    }

    /**
     * Handler scan result
     */
    public void handleDecode(Result result, Bitmap barcode) {
        inactivityTimer.onActivity();
        playBeepSoundAndVibrate();
        String resultString = result.getText();
        if (resultString.equals("")) {
            showScanErrorDialog();
            if (handler != null)
                handler.restartPreviewAndDecode();
            Toast.makeText(NKUScannerActivity.this, "Scan failed!", Toast.LENGTH_SHORT).show();
        } else {
            //testImage.setImageBitmap(barcode);
//            Logger.d("-------qrCode scan result--------" + resultString + "啦啦啦啦");
            isRightCode(resultString);
//            Logger.d("---codeErrorAlert---" + codeErrorAlert);
            if (type == 1) {
                if (isRightCode(resultString)) {
                    String storeCode = resultString.substring(1, 5).trim();
                    String tableCode = resultString.substring(6, 10).trim();
                    /*Intent intent = new Intent(NKUScannerActivity.this, OrderFinishActivity.class);
                    Logger.d("-------storeCode------" + storeCode + "-------tableCode-----" + tableCode);
                    intent.putExtra("storeCode", storeCode);
                    intent.putExtra("tableCode", tableCode);
                    startActivity(intent);*/
                    NKUScannerActivity.this.finish();
                } else {
//                    Logger.d("codeErrorAlert----" + codeErrorAlert);
                    showScanErrorDialog();
                }
            } else if (type == 2) {
//                setSukipass(resultString);
            }
        }
    }

    private static final long VIBRATE_DURATION = 200L;

    private void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }

    private void showScanErrorDialog() {
        final Dialog dialog = new Dialog(this, R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_scan_error);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        TextView tvContent = dialog.findViewById(R.id.tvContent);
        if (isErrorInStore){
            tvContent.setText("お手数ですが店内商品を削除の上、\\n再度QRコードを読込してください。");
        }
        else if (type == 1) {
            tvContent.setText("このQRコードは選択商品に対応していません。\\n該当注文専用のQRコードを読込してください。");
        } else if (type == 2) {
            tvContent.setText("このQRコードはSukipassに対応していない、もしくは無効なSukipassのQRコードです。");
        }
        TextView okBtn = dialog.findViewById(R.id.tvConfirm);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (handler != null)
                    handler.restartPreviewAndDecode();
            }
        });
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER_VERTICAL);
        dialog.show();
    }

    String codeErrorAlert = "";

    private boolean isRightCode(String code) {
        isErrorInStore = false;
        Map<String, Integer> CHECK_MAP = new HashMap<>();
        CHECK_MAP.put("0", 0);
        CHECK_MAP.put("1", 1);
        CHECK_MAP.put("2", 2);
        CHECK_MAP.put("3", 3);
        CHECK_MAP.put("4", 4);
        CHECK_MAP.put("5", 5);
        CHECK_MAP.put("6", 6);
        CHECK_MAP.put("7", 7);
        CHECK_MAP.put("8", 8);
        CHECK_MAP.put("9", 9);
        CHECK_MAP.put("A", 10);
        CHECK_MAP.put("B", 11);
        CHECK_MAP.put("C", 12);
        CHECK_MAP.put("D", 13);
        CHECK_MAP.put("E", 14);
        CHECK_MAP.put("F", 15);
        CHECK_MAP.put("G", 16);
        CHECK_MAP.put("H", 17);
        CHECK_MAP.put("I", 18);
        CHECK_MAP.put("J", 19);
        CHECK_MAP.put("K", 20);
        CHECK_MAP.put("L", 21);
        CHECK_MAP.put("M", 22);
        CHECK_MAP.put("N", 23);
        CHECK_MAP.put("O", 24);
        CHECK_MAP.put("P", 25);
        CHECK_MAP.put("Q", 26);
        CHECK_MAP.put("R", 27);
        CHECK_MAP.put("S", 28);
        CHECK_MAP.put("T", 29);
        CHECK_MAP.put("U", 30);
        CHECK_MAP.put("V", 31);
        CHECK_MAP.put("W", 32);
        CHECK_MAP.put("X", 33);
        CHECK_MAP.put("Y", 34);
        CHECK_MAP.put("Z", 35);
        CHECK_MAP.put("-", 36);
        CHECK_MAP.put(".", 37);
        CHECK_MAP.put(" ", 38);
        CHECK_MAP.put("$", 39);
        CHECK_MAP.put("/", 40);
        CHECK_MAP.put("+", 41);
        CHECK_MAP.put("%", 42);
        char[] codeArray = code.toCharArray();
        if (codeArray.length != 12) {
//            Logger.d("---------------the code length is not 12");
            codeErrorAlert = "the code length is not 12";
            return false;
        }
        String tableCode = code.substring(6, 10).trim();
        if (tableCode.contains("TO") || tableCode.contains("DT")) {
            /*SpoApplication.getCartDatas();
            if (DataMemory.getInstance().CART_DATAS != null
                    && DataMemory.getInstance().CART_DATAS.getData() != null
                    && DataMemory.getInstance().CART_DATAS.getData().size() > 0) {
                boolean hasInStoreMenu = false;
                for (JsonChoicedMenuData data : DataMemory.getInstance().CART_DATAS.getData()) {
                    if (data.getSalesKind() == 1) {// in store
                        hasInStoreMenu = true;
                        break;
                    }
                }
                if (hasInStoreMenu) {
                    codeErrorAlert = "InStore menu  scan 'TO' OR 'DT'";
                    isErrorInStore = true;
                    return false;
                }
            } else {
                codeErrorAlert = "cart is empty and tableCode contains TO";
//                Logger.d("---------" + codeErrorAlert);
                return false;
            }*/
        }
        int sum = 0;
        int lastCharValue = 0;
        for (int i = 0; i < codeArray.length; i++) {
            if (CHECK_MAP.containsKey(String.valueOf(codeArray[i]))) {
                int no = CHECK_MAP.get(String.valueOf(codeArray[i]));
                if (i >= codeArray.length - 1) {
                    lastCharValue += no;
                } else {
                    sum += no;
                }
            }
        }
        if (sum % 43 != lastCharValue) {
            codeErrorAlert = "sum % 43 != lastCharValue";
        } else {
            codeErrorAlert = "success";
        }
        return sum % 43 == lastCharValue;
    }

    public void drawViewfinder() {
        viewfinderView.drawViewfinder();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;
    }
}
