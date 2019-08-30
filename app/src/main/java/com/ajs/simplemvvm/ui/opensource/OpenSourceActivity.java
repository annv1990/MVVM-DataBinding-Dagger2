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
import com.ajs.simplemvvm.ui.opensource.fragment.OpenSourceViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class OpenSourceActivity extends BaseActivity<ActivityOpenSourceBinding, OpenSourceViewModel> implements View.OnClickListener {

    Button btnOpenSource;

    @Inject
    OpenSourceViewModel mOpenSourceViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //TODO Inject Dagger
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        btnOpenSource = findViewById(R.id.btnOpenSource);
        btnOpenSource.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOpenSource: {
                Log.d("ANNV", mOpenSourceViewModel.toString());
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
        return BR.openSourceViewModel;
    }

    @Override
    public OpenSourceViewModel getViewModel() {
        return mOpenSourceViewModel;
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
}
