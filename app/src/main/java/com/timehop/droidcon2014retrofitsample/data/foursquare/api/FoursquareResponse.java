package com.timehop.droidcon2014retrofitsample.data.foursquare.api;

import com.google.gson.annotations.SerializedName;
import com.timehop.droidcon2014retrofitsample.data.foursquare.model.Meta;
import com.timehop.droidcon2014retrofitsample.data.foursquare.model.ResponseWrapper;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FoursquareResponse {
  public static final String FIELD_RESPONSE = "response";
  public static final String FIELD_META = "meta";

  @SerializedName(FIELD_META) @NotNull private Meta meta;
  @SerializedName(FIELD_RESPONSE) @Nullable private ResponseWrapper response;

  @NotNull
  public Meta getMeta() {
    return meta;
  }

  public ResponseWrapper getResponse() {
    return response;
  }
}