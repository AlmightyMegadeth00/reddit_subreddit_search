package com.example.reddit.davekessler_androidcodechallenge.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.example.reddit.davekessler_androidcodechallenge.MainApplication;
import com.example.reddit.davekessler_androidcodechallenge.R;
import com.example.reddit.davekessler_androidcodechallenge.Util.Constants;
import com.example.reddit.davekessler_androidcodechallenge.dagger2.RestClient;
import com.example.reddit.davekessler_androidcodechallenge.models.SearchResultPost;
import com.example.reddit.davekessler_androidcodechallenge.searchresultadapter.SearchResultsAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultsFragment extends BaseFragment {

    private static final String TAG = SearchResultsFragment.class.getSimpleName();

    @BindView(R.id.search_results_recycler_view)
    RecyclerView mSearchResultsRecyclerView;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    public SearchResultsFragment() {
        // fragment containing recyclerview for search results
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View onCreateView = inflater.inflate(R.layout.fragment_search_results, container, false);

        ButterKnife.bind(this, onCreateView);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItems();
            }
        });

        return onCreateView;
    }

    private void refreshItems() {
        mRestClient.refreshSearch();
    }

    private void onItemsLoadComplete() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onCreate(Bundle onSavedInstanceState) {
        super.onCreate(onSavedInstanceState);

        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.SEARCH_RESULTS_UPDATED);
        getActivity().registerReceiver(mReceiver, filter);
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final List<SearchResultPost> results = mRestClient.getLastSearchResults();

            onItemsLoadComplete();

            // this shouldn't be necessary but ¯\_(ツ)_/¯
            if (!results.isEmpty()) {
                RecyclerView.Adapter searchResultsAdapter = new SearchResultsAdapter(results, mSearchResultsRecyclerView);
                RecyclerView.LayoutManager mSearchResultsLayoutManager = new LinearLayoutManager(context);
                mSearchResultsRecyclerView.setLayoutManager(mSearchResultsLayoutManager);
                mSearchResultsRecyclerView.setAdapter(searchResultsAdapter);

            } else {
                Log.e(TAG, "results returned an empty list");
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mReceiver);
    }
}
