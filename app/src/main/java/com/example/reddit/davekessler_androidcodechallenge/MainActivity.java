package com.example.reddit.davekessler_androidcodechallenge;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.reddit.davekessler_androidcodechallenge.Util.Constants;
import com.example.reddit.davekessler_androidcodechallenge.dagger2.RestClient;
import com.example.reddit.davekessler_androidcodechallenge.fragments.SearchControlBarFragment;
import com.example.reddit.davekessler_androidcodechallenge.fragments.SearchResultsFragment;
import com.example.reddit.davekessler_androidcodechallenge.interfaces.RedditApiInterface;
import com.example.reddit.davekessler_androidcodechallenge.models.SearchResult;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends FragmentActivity {

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

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }
}
