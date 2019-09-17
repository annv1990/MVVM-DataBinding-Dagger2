package com.ajs.simplemvvm.ui.scanner;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ajs.simplemvvm.BR;
import com.ajs.simplemvvm.MVVMViewModelProviderFactory;
import com.ajs.simplemvvm.R;
import com.ajs.simplemvvm.base.BaseActivity;
import com.ajs.simplemvvm.base.BaseViewModel;
import com.ajs.simplemvvm.databinding.ActivityScannerBinding;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import javax.inject.Inject;

public class ScannerActivity extends BaseActivity<ActivityScannerBinding, ScannerActivityViewModel> {

    ActivityScannerBinding mBinding;
    ScannerActivityViewModel mViewModel;

    @Inject
    MVVMViewModelProviderFactory mFactory;

    TextView tvCardText;
    Button btStartScan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tvCardText = (TextView) findViewById(R.id.tv_code_text);
        btStartScan = (Button) findViewById(R.id.btn_scan);

        btStartScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQRScanner();
            }
        });
    }

    @Override
    protected int getResourceLayoutId() {
        return R.layout.activity_scanner;
    }

    @Override
    public int getBindingVariable() {
        return BR.scannerActivityVM;
    }

    @Override
    public ScannerActivityViewModel getViewModel() {
        return mViewModel = ViewModelProviders.of(this, mFactory).get(ScannerActivityViewModel.class);
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
}
