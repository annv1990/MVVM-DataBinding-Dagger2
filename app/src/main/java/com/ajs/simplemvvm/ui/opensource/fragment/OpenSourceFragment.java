package com.ajs.simplemvvm.ui.opensource.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.ajs.simplemvvm.BR;
import com.ajs.simplemvvm.R;
import com.ajs.simplemvvm.base.BaseFragment;
import com.ajs.simplemvvm.databinding.FragmentOpenSourceBinding;

import java.util.List;

import javax.inject.Inject;

public class OpenSourceFragment extends BaseFragment<FragmentOpenSourceBinding, OpenSourceFragmentViewModel> {

    FragmentOpenSourceBinding mFragmentOpenSourceBinding;

    @Inject
    OpenSourceFragmentViewModel mOpenSourceFragmentViewModel;
    @Inject
    LinearLayoutManager mLinearLayoutManager;
    @Inject
    OpenSourceAdapter mOpenSourceAdapter;

    public static OpenSourceFragment newInstance() {
        Bundle args = new Bundle();
        OpenSourceFragment fragment = new OpenSourceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentOpenSourceBinding = getViewDataBinding();
        setUp();
        mOpenSourceFragmentViewModel.getOpenSourceListLiveData().observe(mActivity, new Observer<List<OpenSourceItemViewModel>>() {
            @Override
            public void onChanged(@Nullable List<OpenSourceItemViewModel> openSourceItemViewModels) {
                mOpenSourceAdapter = new OpenSourceAdapter(openSourceItemViewModels);
                mFragmentOpenSourceBinding.rvOpenSource.setAdapter(mOpenSourceAdapter);
            }
        });
    }

    @Override
    public int getBindingVariable() {
        return BR.opensourceFragmentViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_open_source;
    }

    @Override
    public OpenSourceFragmentViewModel getViewModel() {
        return mOpenSourceFragmentViewModel;
    }

    private void setUp() {
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentOpenSourceBinding.rvOpenSource.setLayoutManager(mLinearLayoutManager);
        mFragmentOpenSourceBinding.rvOpenSource.setAdapter(mOpenSourceAdapter);
        mFragmentOpenSourceBinding.rvOpenSource.setItemAnimator(new DefaultItemAnimator());
        mOpenSourceFragmentViewModel.fetchRepository();
    }
}
