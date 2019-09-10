package com.ajs.simplemvvm.ui.home.first;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajs.simplemvvm.BR;
import com.ajs.simplemvvm.MVVMViewModelProviderFactory;
import com.ajs.simplemvvm.R;
import com.ajs.simplemvvm.base.BaseFragment;
import com.ajs.simplemvvm.databinding.FragmentFirstBinding;
import com.ajs.simplemvvm.ui.opensource.fragment.OpenSourceFragment;

import javax.inject.Inject;

public class FirstFragment extends BaseFragment<FragmentFirstBinding, FirstFragmentViewModel> {

    @Inject
    MVVMViewModelProviderFactory mFactory;

    FirstFragmentViewModel mFirstFragmentViewModel;

    public static FirstFragment newInstance(){
        Bundle args = new Bundle();
        FirstFragment fragment = new FirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.firsFragmentVM;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_first;
    }

    @Override
    public FirstFragmentViewModel getViewModel() {
        return mFirstFragmentViewModel = ViewModelProviders.of(mActivity, mFactory).get(FirstFragmentViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public FragmentFirstBinding getViewDataBinding() {
        return super.getViewDataBinding();
    }
}
