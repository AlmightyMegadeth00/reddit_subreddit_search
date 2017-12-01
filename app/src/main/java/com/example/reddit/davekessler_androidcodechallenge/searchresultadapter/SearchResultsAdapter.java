package com.example.reddit.davekessler_androidcodechallenge.searchresultadapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reddit.davekessler_androidcodechallenge.R;
import com.example.reddit.davekessler_androidcodechallenge.Util.ViewHolderUtil;
import com.example.reddit.davekessler_androidcodechallenge.models.SearchResult;
import com.example.reddit.davekessler_androidcodechallenge.models.SearchResultPost;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsViewHolder> {

    private static final String TAG = SearchResultsAdapter.class.getSimpleName();

    private final List<SearchResultPost> searchResults;
    private final RecyclerView recyclerView;

    public SearchResultsAdapter(List<SearchResultPost> searchResults, RecyclerView recyclerView) {
        this.searchResults = searchResults;
        this.recyclerView = recyclerView;
    }

    @Override
    public SearchResultsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_result_card_view, null);
        view.setOnClickListener(new RecyclerItemOnClickListener(searchResults));
        return new SearchResultsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchResultsViewHolder holder, int position) {
        holder.postImage.setImageResource(R.drawable.reddit_alien);

        ViewHolderUtil.populateViewHolder(holder, position, searchResults.get(position));
    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    private class RecyclerItemOnClickListener implements View.OnClickListener {

        private final String SUBJECT = "zOMG a Reddit link by";
        private final String BODY = "yo dawg. I heard you like reddit links.  Check this out: ";

        private List<SearchResultPost> searchResults;

        RecyclerItemOnClickListener(List<SearchResultPost> searchResults) {
            this.searchResults = searchResults;
        }

        @Override
        public void onClick(View v) {
            int itemPosition = recyclerView.indexOfChild(v);
            Log.e(TAG, "Clicked and Position is " + String.valueOf(itemPosition));

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, SUBJECT + " " + searchResults.get(itemPosition).getAuthor());
            intent.putExtra(Intent.EXTRA_TEXT, BODY +  getPostLink(itemPosition));

            v.getContext().startActivity(Intent.createChooser(intent, "Share Link:"));
        }

        private String getPostLink(int itemPosition) {
            return searchResults.get(itemPosition).getUrl();        }
    }
}
