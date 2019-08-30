package com.ajs.simplemvvm.ui.opensource.fragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class OpenSourceFragmentProvider {

    @ContributesAndroidInjector
    abstract OpenSourceFragment provideOpenSourceFragment();

}
