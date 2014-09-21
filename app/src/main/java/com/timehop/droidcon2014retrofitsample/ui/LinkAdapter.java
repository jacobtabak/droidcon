package com.timehop.droidcon2014retrofitsample.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.timehop.droidcon2014retrofitsample.R;
import com.timehop.droidcon2014retrofitsample.data.reddit.model.RedditLink;

public class LinkAdapter extends ArrayAdapter<RedditLink> {

  private static final int RED_HOT = 1000;

  public LinkAdapter(Context context) {
    super(context, 0);
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
    setBackgroundColor(convertView, link.getScore());
    return convertView;
  }

  /*
   * set a background based on score, just for kicks
   */
  private static void setBackgroundColor(final View view, final int score) {
      final StateListDrawable sld = new StateListDrawable();
      sld.addState(new int[]{android.R.attr.state_pressed},
              new ColorDrawable(getScoreColor(score)));
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
          view.setBackground(sld);
      } else {
          view.setBackgroundDrawable(sld);
      }
  }

  private static int getScoreColor(final int score) {
    final float redRatio = (float) score / RED_HOT;
    final int red = redRatio <= 1
            ? (int) (redRatio * 255)
            : 255;
    final int blue = 255 - red;
    return Color.argb(100, red, 0, blue);
  }
}
