package com.example.reddit.davekessler_androidcodechallenge.dagger2.modules;

import android.content.Context;

import com.example.reddit.davekessler_androidcodechallenge.dagger2.RestClient;
import com.example.reddit.davekessler_androidcodechallenge.dagger2.UserScope;
import com.example.reddit.davekessler_androidcodechallenge.interfaces.RedditApiInterface;

import dagger.Module;
import dagger.Provides;
import retrofit.Retrofit;

@Module
public class RedditModule {

    private final Context context;

    public RedditModule(Context context) {
        this.context = context;
    }

    @Provides
    @UserScope
    public RestClient providesRestClient(RedditApiInterface redditApiInterface) {
        return new RestClient(context, redditApiInterface);
    }

    @Provides
    @UserScope
    public RedditApiInterface providesRedditInterface(Retrofit retrofit) {
        return retrofit.create(RedditApiInterface.class);
    }
}