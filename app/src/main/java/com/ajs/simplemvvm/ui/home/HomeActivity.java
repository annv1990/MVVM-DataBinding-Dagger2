package com.ajs.simplemvvm.ui.home;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ajs.simplemvvm.BR;
import com.ajs.simplemvvm.MVVMViewModelProviderFactory;
import com.ajs.simplemvvm.R;
import com.ajs.simplemvvm.base.BaseActivity;
import com.ajs.simplemvvm.databinding.ActivityHomeBinding;
import com.ajs.simplemvvm.ui.home.first.FirstFragment;
import com.ajs.simplemvvm.ui.home.second.SecondFragment;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class HomeActivity extends BaseActivity<ActivityHomeBinding, HomeActivityViewModel>
        implements View.OnClickListener, HasSupportFragmentInjector, HomeNavigator {

    ActivityHomeBinding mBinding;

    FirstFragment mFirstFragment;

    SecondFragment mSecondFragment;

    @Inject
    MVVMViewModelProviderFactory mFactory;

    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentDispatchingAndroidInjector;

    LinearLayout navFirst;

    HomeActivityViewModel mViewModel;
    Fragment mActiveFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //TODO Inject Dagger
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        mBinding = getViewDataBinding();
        mViewModel.setNavigator(this);
        mBinding.setClicker(new HomeClicker(mViewModel));
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
                switchFirstFragment();
//                showOpenSourceFragment(mFirstFragment);
                break;
            }
            case R.id.navSecond: {
                switchSecondFragment();
//                showOpenSourceFragment(mSecondFragment);
                break;
            }
        }
    }

    @Override
    public void switchFirstFragment() {
        Toast.makeText(this, "First", Toast.LENGTH_SHORT).show();
        showOpenSourceFragment(mFirstFragment);
    }

    @Override
    public void switchSecondFragment() {
        Toast.makeText(this, "Second", Toast.LENGTH_SHORT).show();
        showOpenSourceFragment(mSecondFragment);
    }

    private void setUp() {
        bindView();
        mFirstFragment = FirstFragment.newInstance();
        mSecondFragment = SecondFragment.newInstance();
        showOpenSourceFragment(mFirstFragment);
    }

    private void bindView() {
        /*navFirst = mBinding.navFirst;*/
        setOnClickListener();
    }

    private void setOnClickListener() {
        /*navFirst.setOnClickListener(this);*/
    }

    private void showOpenSourceFragment(Fragment fragment) {
        Fragment currentFragment = getSupportFragmentManager().findFragmentByTag(fragment.getClass().getSimpleName());
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        for(Fragment temp : fragmentList) {
            getSupportFragmentManager()
                    .beginTransaction().hide(temp).commit();
        }
        if(currentFragment == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .disallowAddToBackStack()
//                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .add(R.id.flFragmentContainer, fragment, fragment.getClass().getSimpleName())
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction().show(currentFragment).commit();
        }
    }

    /**
     * An other way to set click handler in MVVM Databinding
     */
    public class HomeClicker {

        HomeActivityViewModel mViewModel;

        HomeClicker(HomeActivityViewModel viewModel) {
            mViewModel = viewModel;
        }

        public void onClickMe(View v) {
            mViewModel.switchFirstFragment();
        }

    }
}
