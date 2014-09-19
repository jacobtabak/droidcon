package com.timehop.droidcon2014retrofitsample.data.foursquare.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ResponseWrapper {
  public static final String FIELD_VENUES = "venues";

  @SerializedName(FIELD_VENUES) @Nullable private List<Venue> venues;

  @Nullable
  public List<Venue> getVenues() {
    return venues;
  }
}
