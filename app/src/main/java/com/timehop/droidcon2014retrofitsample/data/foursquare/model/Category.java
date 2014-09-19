package com.timehop.droidcon2014retrofitsample.data.foursquare.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class Category {
  public static final String FIELD_ID = "id";
  public static final String FIELD_ICON = "icon";
  public static final String FIELD_SHORT_NAME = "shortName";
  public static final String FIELD_PLURAL_NAME = "pluralName";
  public static final String FIELD_NAME = "name";

  @SerializedName(FIELD_ID) @NotNull private String id;
  @SerializedName(FIELD_ICON) @NotNull private Icon icon;
  @SerializedName(FIELD_SHORT_NAME) @NotNull private String shortName;
  @SerializedName(FIELD_PLURAL_NAME) @NotNull private String pluralName;
  @SerializedName(FIELD_NAME) @NotNull private String name;

  @NotNull
  public String getId() {
    return id;
  }

  public void setId(@NotNull String id) {
    this.id = id;
  }

  @NotNull
  public Icon getIcon() {
    return icon;
  }

  public void setIcon(@NotNull Icon icon) {
    this.icon = icon;
  }

  @NotNull
  public String getShortName() {
    return shortName;
  }

  public void setShortName(@NotNull String shortName) {
    this.shortName = shortName;
  }

  @NotNull
  public String getPluralName() {
    return pluralName;
  }

  public void setPluralName(@NotNull String pluralName) {
    this.pluralName = pluralName;
  }

  @NotNull
  public String getName() {
    return name;
  }

  public void setName(@NotNull String name) {
    this.name = name;
  }
}
