package com.example.reddit.davekessler_androidcodechallenge.dagger2.modules;

import com.example.reddit.davekessler_androidcodechallenge.MainApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainApplicationModule {

    MainApplication mApplication;

    public MainApplicationModule(MainApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    MainApplication providesApplication() {
        return mApplication;
    }
}