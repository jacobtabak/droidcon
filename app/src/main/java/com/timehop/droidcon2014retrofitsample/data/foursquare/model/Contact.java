package com.timehop.droidcon2014retrofitsample.data.foursquare.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Nullable;

public class Contact {
  public static final String FIELD_TWITTER = "twitter";
  public static final String FIELD_PHONE = "phone";
  public static final String FIELD_FORMATTED_PHONE = "formattedPhone";

  @SerializedName(FIELD_TWITTER) @Nullable private String twitter;
  @SerializedName(FIELD_PHONE) @Nullable private String phone;
  @SerializedName(FIELD_FORMATTED_PHONE) @Nullable private String formattedPhone;

  @Nullable
  public String getTwitter() {
    return twitter;
  }

  public void setTwitter(@Nullable String twitter) {
    this.twitter = twitter;
  }

  @Nullable
  public String getPhone() {
    return phone;
  }

  public void setPhone(@Nullable String phone) {
    this.phone = phone;
  }

  @Nullable
  public String getFormattedPhone() {
    return formattedPhone;
  }

  public void setFormattedPhone(@Nullable String formattedPhone) {
    this.formattedPhone = formattedPhone;
  }
}
