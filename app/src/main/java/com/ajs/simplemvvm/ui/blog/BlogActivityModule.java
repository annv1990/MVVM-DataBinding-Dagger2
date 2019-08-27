package com.ajs.simplemvvm.ui.blog;

import dagger.Module;
import dagger.Provides;

@Module
public class BlogActivityModule {

    @Provides
    BlogViewModel provideBlogViewModel(){
        return new BlogViewModel();
    }
}
