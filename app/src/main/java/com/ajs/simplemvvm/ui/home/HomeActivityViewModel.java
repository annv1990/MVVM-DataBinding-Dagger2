package com.ajs.simplemvvm.ui.home;

import com.ajs.simplemvvm.base.BaseViewModel;

public class HomeActivityViewModel extends BaseViewModel<HomeNavigator> {

    public HomeActivityViewModel() {

    }

    public void switchFirstFragment() {
        getNavigator().switchFirstFragment();
    }

    public void switchSecondeFragment() {
        getNavigator().switchSecondFragment();
    }
}
