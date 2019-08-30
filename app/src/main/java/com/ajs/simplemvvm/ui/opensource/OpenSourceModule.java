package com.ajs.simplemvvm.ui.opensource;

import com.ajs.simplemvvm.ui.opensource.fragment.OpenSourceViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class OpenSourceModule {

    @Provides
    OpenSourceViewModel provideOpenSourceViewModel(){
        return new OpenSourceViewModel();
    }
}
