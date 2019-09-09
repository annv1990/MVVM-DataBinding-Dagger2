package com.ajs.simplemvvm.ui.opensource;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ajs.simplemvvm.BR;
import com.ajs.simplemvvm.MVVMViewModelProviderFactory;
import com.ajs.simplemvvm.R;
import com.ajs.simplemvvm.base.BaseActivity;
import com.ajs.simplemvvm.databinding.ActivityOpenSourceBinding;
import com.ajs.simplemvvm.ui.opensource.fragment.OpenSourceFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class OpenSourceActivity extends BaseActivity<ActivityOpenSourceBinding, OpenSourceActivityViewModel> implements View.OnClickListener, HasSupportFragmentInjector {

    Button btnOpenSource;

    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentDispatchingAndroidInjector;

    @Inject
    MVVMViewModelProviderFactory mFactory;

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
        return mOpenSourceActivityViewModel = ViewModelProviders.of(this, mFactory).get(OpenSourceActivityViewModel.class);
    }

    @Override
    public ActivityOpenSourceBinding getViewDataBinding() {
        return super.getViewDataBinding();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mFragmentDispatchingAndroidInjector;
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
