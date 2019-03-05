package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.Viewholder> {

    private Context context;
    private List<Tweet> tweets;

    //Pass in context and list of tweets
    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    // for each row, inflate the layout
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, viewGroup, false);
        return new Viewholder(view);
    }

    // bind values based on the position of the element
    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
        Tweet tweet = tweets.get(i);
        viewholder.tvBody.setText(tweet.body);
        viewholder.tvScreenName.setText(tweet.user.screenName);
        Glide.with(context).load(tweet.user.profileImageUrl).into(viewholder.ivProfileImage);
        viewholder.tvTimestamp.setText(tweet.getFormattedTimestamp());
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addTweets(List<Tweet> tweetList) {
        tweets.addAll(tweetList);
        notifyDataSetChanged();
    }


    // Define the Viewholder
    public class Viewholder extends RecyclerView.ViewHolder {

        public ImageView ivProfileImage;
        public TextView tvScreenName;
        public TextView tvBody;
        public TextView tvTimestamp;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvTimestamp = itemView.findViewById(R.id.tvTimestamp);
        }
    }

}