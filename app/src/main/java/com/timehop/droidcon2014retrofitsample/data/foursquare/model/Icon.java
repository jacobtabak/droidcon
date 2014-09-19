package com.timehop.droidcon2014retrofitsample.data.foursquare.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class Icon {
  public static final String FIELD_PREFIX = "prefix";
  public static final String FIELD_SUFFIX = "suffix";

  @SerializedName(FIELD_PREFIX) @NotNull private String prefix;
  @SerializedName(FIELD_SUFFIX) @NotNull private String suffix;

  @NotNull
  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(@NotNull String prefix) {
    this.prefix = prefix;
  }

  @NotNull
  public String getSuffix() {
    return suffix;
  }

  public void setSuffix(@NotNull String suffix) {
    this.suffix = suffix;
  }
}
