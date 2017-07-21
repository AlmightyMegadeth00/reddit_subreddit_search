package com.example.reddit.davekessler_androidcodechallenge.dagger2.components;

import com.example.reddit.davekessler_androidcodechallenge.dagger2.RestClient;
import com.example.reddit.davekessler_androidcodechallenge.dagger2.modules.MainApplicationModule;
import com.example.reddit.davekessler_androidcodechallenge.dagger2.modules.NetworkModule;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Component;
import retrofit.Retrofit;

@Singleton
@Component(modules={MainApplicationModule.class, NetworkModule.class})
public interface NetworkComponent {
    Retrofit retrofit();
    OkHttpClient okHttpClient();
}