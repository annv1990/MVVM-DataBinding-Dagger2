package com.ajs.simplemvvm.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import dagger.android.AndroidInjection;

public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity {

    private T mViewDataBinding;
    private V mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        performDataBinding();
    }

    protected abstract @LayoutRes int getResourceLayoutId();

    public abstract int getBindingVariable();

    public abstract V getViewModel();

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getResourceLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }

    protected void startLoading(){

    }

    protected void stopLoading(){

    }

}
