package com.timehop.droidcon2014retrofitsample.data.foursquare.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class Menu {
  public static final String FIELD_URL = "url";
  public static final String FIELD_MOBILE_URL = "mobileUrl";

  @SerializedName(FIELD_URL) @NotNull private String url;
  @SerializedName(FIELD_MOBILE_URL) @NotNull private String mobileUrl;

  @NotNull
  public String getUrl() {
    return url;
  }

  public void setUrl(@NotNull String url) {
    this.url = url;
  }

  @NotNull
  public String getMobileUrl() {
    return mobileUrl;
  }

  public void setMobileUrl(@NotNull String mobileUrl) {
    this.mobileUrl = mobileUrl;
  }
}
