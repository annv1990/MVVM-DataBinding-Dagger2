package com.ajs.simplemvvm.ui.opensource;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ajs.simplemvvm.R;
import com.ajs.simplemvvm.base.BaseActivity;
import com.ajs.simplemvvm.base.BaseViewModel;

public class OpenSourceActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getResourceLayoutId() {
        return R.layout.activity_open_source;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public BaseViewModel getViewModel() {
        return null;
    }

    @Override
    public ViewDataBinding getViewDataBinding() {
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
