package com.ajs.simplemvvm.di.component;

import android.app.Application;

import com.ajs.simplemvvm.MVVMApplication;
import com.ajs.simplemvvm.di.module.AppModule;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Component(modules = {AppModule.class, AndroidInjectionModule.class})
public interface AppComponent {

    void inject(MVVMApplication application);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder appplicaiton(Application app);

        AppComponent build();
    }

}
