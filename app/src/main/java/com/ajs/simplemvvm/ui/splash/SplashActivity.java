package com.ajs.simplemvvm.ui.splash;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.ajs.simplemvvm.BR;
import com.ajs.simplemvvm.MVVMViewModelProviderFactory;
import com.ajs.simplemvvm.ui.MainActivity;
import com.ajs.simplemvvm.R;
import com.ajs.simplemvvm.base.BaseActivity;
import com.ajs.simplemvvm.databinding.ActivitySplashBinding;

import javax.inject.Inject;

import dagger.android.HasActivityInjector;

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> {

    ActivitySplashBinding mBinding;
    SplashViewModel mViewModel;

    Handler mHandler;
    Runnable mRunnable;

    @Inject
    MVVMViewModelProviderFactory factory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        };
        mHandler.postDelayed(mRunnable, 3000);
    }

    @Override
    protected int getResourceLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public SplashViewModel getViewModel() {
        return mViewModel = ViewModelProviders.of(this, factory).get(SplashViewModel.class);
    }


    @Override
    public ActivitySplashBinding getViewDataBinding() {
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
