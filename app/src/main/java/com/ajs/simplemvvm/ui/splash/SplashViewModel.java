package com.ajs.simplemvvm.ui.splash;

import com.ajs.simplemvvm.base.BaseViewModel;

import java.util.Calendar;
import java.util.Date;

public class SplashViewModel extends BaseViewModel {

    String title;

    public SplashViewModel(){
        super();
    }

    public String getTitle(){
        Date currentTime = Calendar.getInstance().getTime();
        title = currentTime.toString();
        return title;
    }



}
