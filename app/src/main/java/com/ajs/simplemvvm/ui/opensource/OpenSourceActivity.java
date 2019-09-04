package com.ajs.simplemvvm.ui.opensource;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ajs.simplemvvm.BR;
import com.ajs.simplemvvm.R;
import com.ajs.simplemvvm.base.BaseActivity;
import com.ajs.simplemvvm.databinding.ActivityOpenSourceBinding;
import com.ajs.simplemvvm.ui.opensource.fragment.OpenSourceFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class OpenSourceActivity extends BaseActivity<ActivityOpenSourceBinding, OpenSourceActivityViewModel> implements View.OnClickListener {

    Button btnOpenSource;

    @Inject
    OpenSourceActivityViewModel mOpenSourceActivityViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //TODO Inject Dagger
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);


        btnOpenSource = findViewById(R.id.btnOpenSource);
        btnOpenSource.setOnClickListener(this);
        showOpenSourceFragment();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOpenSource: {
                Log.d("ANNV", "onClick");
                break;
            }
        }
    }

    @Override
    protected int getResourceLayoutId() {
        return R.layout.activity_open_source;
    }

    @Override
    public int getBindingVariable() {
        return BR.openSourceActivityViewModel;
    }

    @Override
    public OpenSourceActivityViewModel getViewModel() {
        return mOpenSourceActivityViewModel;
    }

    @Override
    public ActivityOpenSourceBinding getViewDataBinding() {
        return super.getViewDataBinding();
    }

    @Override
    protected void startLoading() {
        super.startLoading();
    }

    @Override
    protected void stopLoading() {
        super.stopLoading();
    }

    private void showOpenSourceFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
//                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.flFragmentContainer, OpenSourceFragment.newInstance(), "OpenSourceFragment")
                .commit();
    }
}
