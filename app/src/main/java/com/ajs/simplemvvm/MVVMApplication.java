package com.ajs.simplemvvm;

import android.app.Application;

import com.ajs.simplemvvm.di.component.DaggerAppComponent;

import javax.inject.Inject;

public class MVVMApplication extends Application {

    @Inject
    private static Application mInstance;

    public static Application getInstance(){
        synchronized (MVVMApplication.class) {
            if(mInstance == null) {
                mInstance = new MVVMApplication();
            }
        }
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder()
                .appplicaiton(this)
                .build()
                .inject(this);
    }
}
