package com.ajs.simplemvvm.ui.opensource.fragment;

import com.ajs.simplemvvm.BR;
import com.ajs.simplemvvm.R;
import com.ajs.simplemvvm.base.BaseFragment;
import com.ajs.simplemvvm.base.BaseViewModel;

import javax.inject.Inject;

public class OpenSourceFragment extends BaseFragment {

    @Override
    public int getBindingVariable() {
        return BR.openSourceViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_open_source;
    }

    @Override
    public BaseViewModel getViewModel() {
        return null;
    }
}
