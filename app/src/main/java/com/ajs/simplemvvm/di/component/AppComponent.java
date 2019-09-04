package com.ajs.simplemvvm.di.component;

import android.app.Application;

import com.ajs.simplemvvm.MVVMApplication;
import com.ajs.simplemvvm.di.builder.ActivityBuilder;
import com.ajs.simplemvvm.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * @Component os a bridge between @Module and @Inject
 */
@Singleton
@Component(modules = {AppModule.class, AndroidInjectionModule.class, ActivityBuilder.class})
public interface AppComponent {

    // where dagger will inject instance
    void inject(MVVMApplication application);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application app);

        AppComponent build();
    }

}
