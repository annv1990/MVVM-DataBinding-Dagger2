package com.ajs.simplemvvm.base;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

public abstract class BaseViewModel extends ViewModel {

    private final ObservableBoolean mIsLoading = new ObservableBoolean();

    public BaseViewModel() {
        super();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }
}
