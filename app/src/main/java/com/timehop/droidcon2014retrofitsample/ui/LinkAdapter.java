package com.timehop.droidcon2014retrofitsample.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.timehop.droidcon2014retrofitsample.R;
import com.timehop.droidcon2014retrofitsample.data.reddit.model.RedditLink;

public class LinkAdapter extends ArrayAdapter<RedditLink> {
  public LinkAdapter(Context context) {
    super(context, R.layout.view_link);
  }

    private static class ViewHolder {

        private final TextView scoreTextView;
        private final TextView titleTextView;
        private final TextView authorTextView;

        private ViewHolder(final View view) {
            scoreTextView = (TextView) view.findViewById(R.id.score_textview);
            titleTextView = (TextView) view.findViewById(R.id.title_textview);
            authorTextView = (TextView) view.findViewById(R.id.author_textview);
        }
    }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
      final RedditLink link = getItem(position);
      final ViewHolder vh;
      if (convertView == null) {
          convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_link, parent, false);
          vh = new ViewHolder(convertView);
          convertView.setTag(vh);
      } else {
          vh = (ViewHolder) convertView.getTag();
      }
      vh.scoreTextView.setText(String.valueOf(link.getScore()));
      vh.titleTextView.setText(String.valueOf(link.getTitle()));
      vh.authorTextView.setText(String.valueOf(link.getAuthor()));
    return convertView;
  }
}
