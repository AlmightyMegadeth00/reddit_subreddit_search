package com.example.reddit.davekessler_androidcodechallenge.fragments;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.reddit.davekessler_androidcodechallenge.MainApplication;
import com.example.reddit.davekessler_androidcodechallenge.R;
import com.example.reddit.davekessler_androidcodechallenge.dagger2.RestClient;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchControlBarFragment extends Fragment {

    private static final String TAG = SearchControlBarFragment.class.getSimpleName();

    @BindView(R.id.search_edit_text)
    EditText mSearchEditText;

    @Inject
    RestClient mRestClient;

    public SearchControlBarFragment() {
        // fragment to contain the search bar
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MainApplication) getActivity().getApplication()).getRedditComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View onCreateView = inflater.inflate(R.layout.fragment_search_control_bar, container, false);

        ButterKnife.bind(this, onCreateView);

        // make sure teh keyboard closes and the enter key submits the text.
        mSearchEditText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_UP) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    handleSearch();

                    InputMethodManager imm =
                            (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mSearchEditText.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        return onCreateView;
    }

    private void handleSearch() {
        final String subreddit = mSearchEditText.getText().toString();
        if (!TextUtils.isEmpty(subreddit)) {
            mRestClient.requestSearchResults(mSearchEditText.getText().toString());
        }
    }

    @OnClick(R.id.clear_search_button)
    public void clearSearhText() {
        mSearchEditText.getText().clear();
        mSearchEditText.clearFocus();
    }

    @OnClick(R.id.search_button)
    public void search() {
        handleSearch();
    }
}
