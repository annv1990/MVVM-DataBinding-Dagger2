package com.ajs.simplemvvm;

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
