package com.ajs.simplemvvm.ui.blog;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ajs.simplemvvm.base.BaseActivity;
import com.ajs.simplemvvm.base.BaseViewModel;

public class BlogActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getResourceLayoutId() {
        return 0;
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
}
