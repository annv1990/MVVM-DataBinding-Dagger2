package com.ajs.simplemvvm;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.ajs.simplemvvm.ui.home.HomeActivityViewModel;
import com.ajs.simplemvvm.ui.home.first.FirstFragmentViewModel;
import com.ajs.simplemvvm.ui.home.second.SecondFragmentViewModel;
import com.ajs.simplemvvm.ui.opensource.OpenSourceActivityViewModel;
import com.ajs.simplemvvm.ui.scanner.ScannerActivityViewModel;
import com.ajs.simplemvvm.ui.splash.SplashViewModel;

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
        } else if (modelClass.isAssignableFrom(FirstFragmentViewModel.class)) {
            return (T) new FirstFragmentViewModel();
        } else if (modelClass.isAssignableFrom(SecondFragmentViewModel.class)) {
            return (T) new SecondFragmentViewModel();
        } else if (modelClass.isAssignableFrom(HomeActivityViewModel.class)) {
            return (T) new HomeActivityViewModel();
        } else if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            return (T) new SplashViewModel();
        }
        // ScannerActivityViewModel.class
        else if (modelClass.isAssignableFrom(ScannerActivityViewModel.class)) {
            return (T) new ScannerActivityViewModel();
        }
        return super.create(modelClass);
    }
}
