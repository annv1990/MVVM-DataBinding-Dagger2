package com.ajs.simplemvvm.di.builder;

import com.ajs.simplemvvm.ui.MainActivity;
import com.ajs.simplemvvm.ui.blog.BlogActivity;
import com.ajs.simplemvvm.ui.blog.BlogActivityModule;
import com.ajs.simplemvvm.ui.home.HomeActivity;
import com.ajs.simplemvvm.ui.home.HomeActivityModule;
import com.ajs.simplemvvm.ui.home.first.FirstFragmentProvider;
import com.ajs.simplemvvm.ui.home.second.SecondFragmentProvider;
import com.ajs.simplemvvm.ui.nkuscanner.NKUScannerActivity;
import com.ajs.simplemvvm.ui.opensource.OpenSourceActivity;
import com.ajs.simplemvvm.ui.opensource.OpenSourceModule;
import com.ajs.simplemvvm.ui.opensource.fragment.OpenSourceFragmentProvider;
import com.ajs.simplemvvm.ui.scanner.ScannerActivity;
import com.ajs.simplemvvm.ui.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = BlogActivityModule.class)
    abstract BlogActivity bindBlogActivity();

    @ContributesAndroidInjector()
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector()
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector(modules = {
            OpenSourceModule.class,
            OpenSourceFragmentProvider.class
    })
    abstract OpenSourceActivity bindOpenSourceActivity();


    @ContributesAndroidInjector(modules = {HomeActivityModule.class, FirstFragmentProvider.class, SecondFragmentProvider.class
            /*FirstFragnentModule.class,
            SecondFragnentModule.class*/})
    abstract HomeActivity bindHomeActivity();

    @ContributesAndroidInjector()
    abstract ScannerActivity bindScannerActivity();

    @ContributesAndroidInjector()
    abstract NKUScannerActivity bindNKNkuScannerActivity();

}
