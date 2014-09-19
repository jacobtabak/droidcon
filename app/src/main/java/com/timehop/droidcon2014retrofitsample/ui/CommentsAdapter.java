package com.timehop.droidcon2014retrofitsample.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.timehop.droidcon2014retrofitsample.R;
import com.timehop.droidcon2014retrofitsample.data.reddit.model.RedditComment;

public class CommentsAdapter extends ArrayAdapter<RedditComment> {
  public CommentsAdapter(Context context) {
    super(context, 0);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.view_comment, parent, false);
    RedditComment comment = getItem(position);
    TextView scoreTextView = (TextView) view.findViewById(R.id.score_textview);
    TextView bodyTextView = (TextView) view.findViewById(R.id.body_textview);
    scoreTextView.setText(String.valueOf(comment.getScore()));
    bodyTextView.setText(comment.getBody());
    int padding = getContext().getResources().getDimensionPixelSize(R.dimen.comment_padding);
    view.setPadding(comment.getDepth() * padding, 0, 0, 0);
    return view;
  }
}
