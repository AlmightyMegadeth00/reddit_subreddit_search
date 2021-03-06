package com.example.reddit.davekessler_androidcodechallenge.dagger2.components;

import com.example.reddit.davekessler_androidcodechallenge.MainActivity;
import com.example.reddit.davekessler_androidcodechallenge.dagger2.UserScope;
import com.example.reddit.davekessler_androidcodechallenge.dagger2.modules.RedditModule;
import com.example.reddit.davekessler_androidcodechallenge.fragments.SearchControlBarFragment;
import com.example.reddit.davekessler_androidcodechallenge.fragments.SearchResultsFragment;

import dagger.Component;

@UserScope
@Component(dependencies = NetworkComponent.class, modules = RedditModule.class)
public interface RedditComponent {
    void inject(MainActivity activity);
    void inject(SearchControlBarFragment fragment);
    void inject(SearchResultsFragment fragment);
}