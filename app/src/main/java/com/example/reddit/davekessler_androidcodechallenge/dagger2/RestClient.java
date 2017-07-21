package com.example.reddit.davekessler_androidcodechallenge.dagger2;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.example.reddit.davekessler_androidcodechallenge.Util.Constants;
import com.example.reddit.davekessler_androidcodechallenge.interfaces.RedditApiInterface;
import com.example.reddit.davekessler_androidcodechallenge.models.SearchResult;
import com.example.reddit.davekessler_androidcodechallenge.models.SearchResultPost;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.processors.BehaviorProcessor;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

@Singleton
public class RestClient implements Callback<SearchResult> {

    private static final String TAG = RestClient.class.getSimpleName();

    private final RedditApiInterface redditApiInterface;
    private final Context context;

    private SearchResult mLastSearchResult = new SearchResult();

    private String mPreviousSubReddit = Constants.DEFAULT_SEARCH;

    public RestClient(Context context, RedditApiInterface redditApiInterface) {
        this.context = context;
        this.redditApiInterface = redditApiInterface;
    }

    public void requestSearchResults(@NonNull String subredditRequest) {
        mPreviousSubReddit = subredditRequest;

        subredditRequest = subredditRequest.replaceAll("\\s","");
        Call<SearchResult> call = redditApiInterface.getSearchResults(subredditRequest);
        call.enqueue(this);
    }

    public List<SearchResultPost> getLastSearchResults() {
        List<SearchResultPost> results = new ArrayList<>();
        for (int i = 0, size = mLastSearchResult.getData().getChildren().size(); i < size; i++) {
            results.add(mLastSearchResult.getData().getChildren().get(i).getData());
        }
        return results;
    }

    public void refreshSearch() {
        Log.i(TAG, "repeating search for subreddit: " + mPreviousSubReddit);
        requestSearchResults(mPreviousSubReddit);
    }

    @Override
    public void onResponse(Response<SearchResult> response, Retrofit retrofit) {
        if (response.isSuccess()) {
            mLastSearchResult = response.body();

            // TODO: implement rxjava 2
            Intent intent = new Intent(Constants.SEARCH_RESULTS_UPDATED);
            context.sendBroadcast(intent);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
    }
}
