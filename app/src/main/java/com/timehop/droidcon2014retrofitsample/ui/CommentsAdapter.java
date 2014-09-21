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

    private static class ViewHolder {

        private final TextView scoreTextView;
        private final TextView bodyTextView;

        private ViewHolder(final View view) {
            scoreTextView = (TextView) view.findViewById(R.id.score_textview);
            bodyTextView = (TextView) view.findViewById(R.id.body_textview);
        }
    }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
      final RedditComment comment = getItem(position);
      final ViewHolder vh;
      if (convertView == null) {
          convertView = LayoutInflater.from(getContext())
                  .inflate(R.layout.view_comment, parent, false);
          vh = new ViewHolder(convertView);
          convertView.setTag(vh);
      } else {
          vh = (ViewHolder) convertView.getTag();
      }
      vh.scoreTextView.setText(String.valueOf(comment.getScore()));
      vh.bodyTextView.setText(comment.getBody());
      final int padding = getContext().getResources()
              .getDimensionPixelSize(R.dimen.comment_padding);
      convertView.setPadding(comment.getDepth() * padding, 0, 0, 0);
      return convertView;
  }
}
