package com.ajs.simplemvvm;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.ajs.simplemvvm.ui.opensource.OpenSourceActivityViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MVVMViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    @Inject
    public MVVMViewModelProviderFactory() {
        super();
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(OpenSourceActivityViewModel.class)) {
            return (T) new OpenSourceActivityViewModel();
        }
        return super.create(modelClass);
    }
}
