package com.ajs.simplemvvm.ui.opensource.fragment;

import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class OpenSourceFragmentModule {

    @Provides
    LinearLayoutManager provideLinearLayoutManager(OpenSourceFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }

    @Provides
    OpenSourceAdapter provideOpenSourceAdapter(){
        return new OpenSourceAdapter(new ArrayList<>());
    }

}
