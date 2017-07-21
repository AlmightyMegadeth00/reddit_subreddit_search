package com.example.reddit.davekessler_androidcodechallenge.interfaces;

import com.example.reddit.davekessler_androidcodechallenge.models.SearchResult;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

public interface RedditApiInterface {
    @GET("/r/{subreddit}/.json")
    Call<SearchResult> getSearchResults(@Path("subreddit") String subreddit);

}