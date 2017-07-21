package com.example.reddit.davekessler_androidcodechallenge.searchresultadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reddit.davekessler_androidcodechallenge.R;
import com.example.reddit.davekessler_androidcodechallenge.dagger2.ui.CustomFontTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class SearchResultsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.search_result_post_image)
    public CircleImageView postImage;

    @BindView(R.id.search_result_post_title)
    public CustomFontTextView postTitle;

    @BindView(R.id.search_result_post_summary)
    public CustomFontTextView postSummary;

    @BindView(R.id.search_result_comments)
    public CustomFontTextView comments;

    @BindView(R.id.search_result_ups)
    public CustomFontTextView ups;

    @BindView(R.id.search_result_downs)
    public CustomFontTextView downs;

    public SearchResultsViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
