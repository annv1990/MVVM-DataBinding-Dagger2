package com.ajs.simplemvvm.ui.home;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

import com.ajs.simplemvvm.BR;
import com.ajs.simplemvvm.MVVMViewModelProviderFactory;
import com.ajs.simplemvvm.R;
import com.ajs.simplemvvm.base.BaseActivity;
import com.ajs.simplemvvm.databinding.ActivityHomeBinding;
import com.ajs.simplemvvm.ui.home.first.FirstFragment;
import com.ajs.simplemvvm.ui.home.second.SecondFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class HomeActivity extends BaseActivity<ActivityHomeBinding, HomeActivityViewModel>
        implements View.OnClickListener, HasSupportFragmentInjector {

    ActivityHomeBinding mBinding;

    /*@Inject*/
    FirstFragment mFirstFragment;

   /* @Inject*/
    SecondFragment mSecondFragment;

    @Inject
    MVVMViewModelProviderFactory mFactory;

    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentDispatchingAndroidInjector;

    HomeActivityViewModel mViewModel;
    Fragment mActiveFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //TODO Inject Dagger
        AndroidInjection.inject(this);
        mBinding = getViewDataBinding();
        super.onCreate(savedInstanceState);
        setUp();
    }

    @Override
    protected int getResourceLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public int getBindingVariable() {
        return BR.homeActivityVM;
    }

    @Override
    public HomeActivityViewModel getViewModel() {
        return mViewModel = ViewModelProviders.of(this, mFactory).get(HomeActivityViewModel.class);
    }

    @Override
    public ActivityHomeBinding getViewDataBinding() {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.navFirst: {
                showOpenSourceFragment(mFirstFragment);
                break;
            }
            case R.id.navSecond: {
                showOpenSourceFragment(mSecondFragment);
                break;
            }
        }
    }

    private void setUp(){
        setOnClickListener();
        mFirstFragment = FirstFragment.newInstance();
        mSecondFragment = SecondFragment.newInstance();
        showOpenSourceFragment(mFirstFragment);
    }

    private void setOnClickListener(){
       /* mBinding.bottomNavigation.navFirst.setOnClickListener(this);
        mBinding.bottomNavigation.navSecond.setOnClickListener(this);*/
    }

    private void showOpenSourceFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
//                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.flFragmentContainer, fragment, fragment.getClass().getSimpleName())
                .commit();
    }
}
