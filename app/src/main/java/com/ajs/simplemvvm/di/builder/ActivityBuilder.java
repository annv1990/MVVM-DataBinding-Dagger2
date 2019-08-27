package com.ajs.simplemvvm.di.builder;

import com.ajs.simplemvvm.ui.MainActivity;
import com.ajs.simplemvvm.ui.blog.BlogActivity;
import com.ajs.simplemvvm.ui.blog.BlogActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = BlogActivityModule.class)
    abstract BlogActivity bindBlogActivity();

    @ContributesAndroidInjector()
    abstract MainActivity bindMainActivity();
}
