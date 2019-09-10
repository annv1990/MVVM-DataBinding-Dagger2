package com.ajs.simplemvvm.ui.home.second;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SecondFragmentProvider {

    @ContributesAndroidInjector(modules = SecondFragmentModule.class)
    abstract SecondFragment provideOpenSourceFragment();

}
