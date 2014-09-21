package com.timehop.droidcon2014retrofitsample.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.timehop.droidcon2014retrofitsample.R;
import com.timehop.droidcon2014retrofitsample.data.foursquare.model.Venue;

public class VenueAdapter extends ArrayAdapter<Venue> {

  public VenueAdapter(Context context) {
    super(context, R.layout.view_venue);
  }

    private static class ViewHolder {

        private final TextView nameView;
        private final TextView locationView;

        private ViewHolder(final View view) {
            nameView = (TextView) view.findViewById(R.id.venue_name);
            locationView = (TextView) view.findViewById(R.id.venue_location);
        }
    }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
      final Venue venue = getItem(position);
      final ViewHolder vh;
      if (convertView == null) {
          convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_venue, parent, false);
          vh = new ViewHolder(convertView);
          convertView.setTag(vh);
      } else {
          vh = (ViewHolder) convertView.getTag();
      }
    vh.nameView.setText(venue.getName());
    vh.locationView.setText(null);
    vh.locationView.setText(venue.getLocation().getAddress());
    return convertView;
  }
}
