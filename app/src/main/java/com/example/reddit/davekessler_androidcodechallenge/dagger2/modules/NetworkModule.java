package com.example.reddit.davekessler_androidcodechallenge.dagger2.modules;

import com.example.reddit.davekessler_androidcodechallenge.MainApplication;
import com.example.reddit.davekessler_androidcodechallenge.Util.GsonTypeAdapter;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

@Module
public class NetworkModule {

    private final String baseUrl;

    public NetworkModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(MainApplication application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }


    @Provides  // Dagger will only look for methods annotated with @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gsonBuilder.registerTypeAdapter(Boolean.class, GsonTypeAdapter.booleanAsIntAdapter);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setCache(cache);
        return okHttpClient;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();
    }
}