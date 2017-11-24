package com.example.reddit.davekessler_androidcodechallenge;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.reddit.davekessler_androidcodechallenge.Util.Constants;
import com.example.reddit.davekessler_androidcodechallenge.dagger2.RestClient;
import com.example.reddit.davekessler_androidcodechallenge.fragments.SearchControlBarFragment;
import com.example.reddit.davekessler_androidcodechallenge.fragments.SearchResultsFragment;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    RestClient mRestClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MainApplication) getApplication()).getRedditComponent().inject(this);

        SearchControlBarFragment searchControlBarFragment = new SearchControlBarFragment();
        SearchResultsFragment searchResultsFragment = new SearchResultsFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.add(R.id.search_bar_frame_layout, searchControlBarFragment, SearchControlBarFragment.class.getSimpleName());
        transaction.add(R.id.search_results_frame_layout, searchResultsFragment, SearchResultsFragment.class.getSimpleName());
        transaction.commit();

        mRestClient.requestSearchResults(Constants.DEFAULT_SEARCH);
    }
}
