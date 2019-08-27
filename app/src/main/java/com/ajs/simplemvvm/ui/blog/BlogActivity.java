package com.ajs.simplemvvm.ui.blog;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ajs.simplemvvm.BR;
import com.ajs.simplemvvm.R;
import com.ajs.simplemvvm.base.BaseActivity;
import com.ajs.simplemvvm.databinding.ActivityBlogBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class BlogActivity extends BaseActivity<ActivityBlogBinding, BlogViewModel> {

    ActivityBlogBinding mBinding;

    @Inject
    BlogViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        mViewModel.fetchOpenSource();
        Log.d("ANNV", "list repos " + mViewModel.repos.size());
    }

    @Override
    protected int getResourceLayoutId() {
        return R.layout.activity_blog;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public BlogViewModel getViewModel() {
      /*
      if(mViewModel == null)
            mViewModel = ViewModelProviders.of(this).get(BlogViewModel.class);*/
        return mViewModel;
    }

    @Override
    public ActivityBlogBinding getViewDataBinding() {
        return super.getViewDataBinding();
    }
}
