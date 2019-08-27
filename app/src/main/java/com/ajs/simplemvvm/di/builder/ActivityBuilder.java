package com.ajs.simplemvvm.di.builder;

import com.ajs.simplemvvm.ui.MainActivity;
import com.ajs.simplemvvm.ui.blog.BlogActivity;
import com.ajs.simplemvvm.ui.blog.BlogActivityModule;
import com.ajs.simplemvvm.ui.opensource.OpenSourceActivity;
import com.ajs.simplemvvm.ui.opensource.OpenSourceFragmentProvider;
import com.ajs.simplemvvm.ui.opensource.OpenSourceModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = BlogActivityModule.class)
    abstract BlogActivity bindBlogActivity();

    @ContributesAndroidInjector()
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {
            OpenSourceModule.class,
            OpenSourceFragmentProvider.class,
    })
    abstract OpenSourceActivity bindOpenSourceActivity();


}
