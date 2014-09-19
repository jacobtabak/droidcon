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
    super(context, 0);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    Venue venue = getItem(position);
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.view_venue, parent, false);
    TextView nameView = (TextView) view.findViewById(R.id.venue_name);
    TextView locationView = (TextView) view.findViewById(R.id.venue_location);
    nameView.setText(venue.getName());
    locationView.setText(null);
    locationView.setText(venue.getLocation().getAddress());
    return view;
  }
}
