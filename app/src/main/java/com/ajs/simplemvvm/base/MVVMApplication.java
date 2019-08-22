package com.ajs.simplemvvm.base;

import android.app.Application;

public class MVVMApplication extends Application {

    private static MVVMApplication mInstance;

    public static MVVMApplication getInstance(){
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
    }
}
