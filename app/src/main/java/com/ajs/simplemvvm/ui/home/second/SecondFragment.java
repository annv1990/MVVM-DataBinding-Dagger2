package com.ajs.simplemvvm.ui.home.second;

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
import com.ajs.simplemvvm.base.BaseViewModel;
import com.ajs.simplemvvm.databinding.FragmentSecondBinding;
import com.ajs.simplemvvm.ui.home.first.FirstFragment;

import java.util.Random;

import javax.inject.Inject;

public class SecondFragment extends BaseFragment<FragmentSecondBinding, SecondFragmentViewModel> {

    FragmentSecondBinding mFragmentSecondBinding;

    @Inject
    MVVMViewModelProviderFactory mFactory;

    SecondFragmentViewModel mSecondFragmentViewModel;

    public static SecondFragment newInstance(){
        Bundle args = new Bundle();
        SecondFragment fragment = new SecondFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.secondFragmentVM;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_second;
    }

    @Override
    public SecondFragmentViewModel getViewModel() {
        return mSecondFragmentViewModel = ViewModelProviders.of(mActivity, mFactory).get(SecondFragmentViewModel.class);
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
        mFragmentSecondBinding = getViewDataBinding();
        mFragmentSecondBinding.tvRandom.setText("" + new Random().nextInt());
    }

}
