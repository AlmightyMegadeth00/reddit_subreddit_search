package com.example.reddit.davekessler_androidcodechallenge.Util;

import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.reddit.davekessler_androidcodechallenge.R;
import com.example.reddit.davekessler_androidcodechallenge.models.SearchResultPost;
import com.example.reddit.davekessler_androidcodechallenge.searchresultadapter.SearchResultsViewHolder;

import io.reactivex.annotations.NonNull;

public class ViewHolderUtil {
    private static final String TAG = ViewHolderUtil.class.getSimpleName();

    private ViewHolderUtil() {
        throw new RuntimeException("no instantiation for you!!");
    }

    public static RecyclerView.ViewHolder populateViewHolder(
            @NonNull SearchResultsViewHolder holder, int position, SearchResultPost post) {
        // load image
        if (!TextUtils.isEmpty(post.getThumbnail())) {
            loadImageResource(holder, post.getThumbnail());
        }

        // load author
        if (!TextUtils.isEmpty(post.getAuthor())) {
            loadPostAuthor(holder, post.getAuthor());
        }

        // load title
        if (!TextUtils.isEmpty(post.getTitle())) {
            loadPostTitle(holder, post.getTitle());
        }

        // load comments
        if (post.getNumComments() != null) {
            loadPostComment(holder, post.getNumComments());
        }

        // load ups
        if (post.getUps() != null) {
            loadPostUps(holder, post.getUps());
        }

        // load downs
        if (post.getDowns() != null) {
            loadPostDowns(holder, post.getDowns());
        }

        return holder;
    }

    private static void loadImageResource(SearchResultsViewHolder holder, String image) {
        if (image.equals("self")) {
            // default listed in xml resource
            Log.e(TAG, "image was \"self\"");
            return;
        }

        // TODO: Placeholder was not available with circle transform.  Need to evaluate Glide 4.0.0 release candidates
        // for stability and compatibility with circle cropped images.. otherwise evaluate picasso.
        Glide.with(holder.postImage.getContext())
                .load(image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.postImage);
    }

    private static void loadPostAuthor(SearchResultsViewHolder holder, String author) {
        if (!TextUtils.isEmpty(author)) {
            holder.postTitle.setText(author);
        } else {
            holder.postTitle.setText("");
        }
    }

    private static void loadPostTitle(SearchResultsViewHolder holder, String title) {
        if (!TextUtils.isEmpty(title)) {
            holder.postSummary.setText(title);
        } else {
            holder.postSummary.setText("");
        }
    }

    private static void loadPostComment(SearchResultsViewHolder holder, int comments) {
        holder.comments.setText(
                holder.comments.getContext().getResources().getString(R.string.search_results_comments, comments));
    }

    private static void loadPostUps(SearchResultsViewHolder holder, int ups) {
        holder.ups.setText(
                holder.ups.getContext().getResources().getString(R.string.search_results_ups, ups));
    }

    private static void loadPostDowns(SearchResultsViewHolder holder, int downs) {
        holder.downs.setText(
                holder.downs.getContext().getResources().getString(R.string.search_results_downs, downs));
    }
}
