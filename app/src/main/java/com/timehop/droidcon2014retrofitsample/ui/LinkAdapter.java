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
    super(context, 0);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    RedditLink link = getItem(position);
    View view = LayoutInflater.from(getContext()).inflate(R.layout.view_link, parent, false);
    TextView scoreTextView = (TextView) view.findViewById(R.id.score_textview);
    TextView titleTextView = (TextView) view.findViewById(R.id.title_textview);
    TextView authorTextView = (TextView) view.findViewById(R.id.author_textview);

    scoreTextView.setText(String.valueOf(link.getScore()));
    titleTextView.setText(String.valueOf(link.getTitle()));
    authorTextView.setText(String.valueOf(link.getAuthor()));

    return view;
  }
}
