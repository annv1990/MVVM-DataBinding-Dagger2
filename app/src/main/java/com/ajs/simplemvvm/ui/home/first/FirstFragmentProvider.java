package com.ajs.simplemvvm.ui.home.first;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FirstFragmentProvider {

    @ContributesAndroidInjector(modules = FirstFragmentModule.class)
    abstract FirstFragment provideOpenSourceFragment();

}
