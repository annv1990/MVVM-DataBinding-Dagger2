package com.ajs.simplemvvm.ui.opensource.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.ajs.simplemvvm.BR;
import com.ajs.simplemvvm.R;
import com.ajs.simplemvvm.base.BaseFragment;
import com.ajs.simplemvvm.base.BaseViewModel;
import com.ajs.simplemvvm.databinding.ActivityOpenSourceBinding;
import com.ajs.simplemvvm.databinding.FragmentOpenSourceBinding;

import javax.inject.Inject;

public class OpenSourceFragment extends BaseFragment<FragmentOpenSourceBinding, OpenSourceFragmentViewModel> {



    FragmentOpenSourceBinding mFragmentOpenSourceBinding;

    @Inject
    OpenSourceFragmentViewModel mOpenSourceFragmentViewModel;

    @Inject
    LinearLayoutManager mLinearLayoutManager;

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
        /*mFragmentOpenSourceBinding.openSourceRecyclerView.setLayoutManager(mLayoutManager);
        mFragmentOpenSourceBinding.openSourceRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mFragmentOpenSourceBinding.openSourceRecyclerView.setAdapter(mOpenSourceAdapter);*/
    }
}
