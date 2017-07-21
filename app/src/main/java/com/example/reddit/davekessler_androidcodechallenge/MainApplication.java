package com.example.reddit.davekessler_androidcodechallenge;

import android.app.Application;

import com.example.reddit.davekessler_androidcodechallenge.Util.Constants;
import com.example.reddit.davekessler_androidcodechallenge.dagger2.components.DaggerNetworkComponent;
import com.example.reddit.davekessler_androidcodechallenge.dagger2.components.DaggerRedditComponent;
import com.example.reddit.davekessler_androidcodechallenge.dagger2.components.NetworkComponent;
import com.example.reddit.davekessler_androidcodechallenge.dagger2.components.RedditComponent;
import com.example.reddit.davekessler_androidcodechallenge.dagger2.modules.MainApplicationModule;
import com.example.reddit.davekessler_androidcodechallenge.dagger2.modules.NetworkModule;
import com.example.reddit.davekessler_androidcodechallenge.dagger2.modules.RedditModule;

public class MainApplication extends Application {

    private static final String TAG = MainApplication.class.getSimpleName();

    private NetworkComponent mNetworkComponent;
    private RedditComponent mRedditComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetworkComponent = DaggerNetworkComponent.builder()
                .mainApplicationModule(new MainApplicationModule(this))
                .networkModule(new NetworkModule(Constants.BASE_REST_URL))
                .build();

        mRedditComponent = DaggerRedditComponent.builder()
                .networkComponent(mNetworkComponent)
                .redditModule(new RedditModule(getApplicationContext()))
                .build();
    }

    public NetworkComponent getNetComponent() {
        return mNetworkComponent;
    }

    public RedditComponent getRedditComponent() {
        return mRedditComponent;
    }
}
