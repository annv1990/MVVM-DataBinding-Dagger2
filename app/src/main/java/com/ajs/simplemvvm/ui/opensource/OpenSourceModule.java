package com.ajs.simplemvvm.ui.opensource;

import dagger.Module;
import dagger.Provides;

@Module
public class OpenSourceModule {

    @Provides
    OpenSourceActivityViewModel provideOpenSourceActivityViewModel(){
        return new OpenSourceActivityViewModel();
    }
}
