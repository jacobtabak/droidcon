package com.timehop.droidcon2014retrofitsample.data.foursquare.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Venue {
  public static final String FIELD_VENUES = "venues";
  public static final String FIELD_ID = "id";
  public static final String FIELD_NAME = "name";
  public static final String FIELD_CONTACT = "contact";
  public static final String FIELD_LOCATION = "location";
  public static final String FIELD_CATEGORIES = "categories";
  public static final String FIELD_VERIFIED = "verified";
  public static final String FIELD_STATS = "stats";
  public static final String FIELD_URL = "url";
  public static final String FIELD_MENU = "menu";

  @SerializedName(FIELD_ID) @NotNull private String id;
  @SerializedName(FIELD_NAME) @NotNull private String name;
  @SerializedName(FIELD_CONTACT) @NotNull private Contact contact;
  @SerializedName(FIELD_LOCATION) @NotNull private Location location;
  @SerializedName(FIELD_CATEGORIES) @NotNull private List<Category> categories;
  @SerializedName(FIELD_VERIFIED) private boolean verified;
  @SerializedName(FIELD_STATS) @NotNull private Stats stats;
  @SerializedName(FIELD_URL) @Nullable private String url;
  @SerializedName(FIELD_MENU) @Nullable private Menu menu;

  @NotNull
  public String getId() {
    return id;
  }

  public void setId(@NotNull String id) {
    this.id = id;
  }

  @NotNull
  public String getName() {
    return name;
  }

  public void setName(@NotNull String name) {
    this.name = name;
  }

  @NotNull
  public Contact getContact() {
    return contact;
  }

  public void setContact(@NotNull Contact contact) {
    this.contact = contact;
  }

  @NotNull
  public Location getLocation() {
    return location;
  }

  public void setLocation(@NotNull Location location) {
    this.location = location;
  }

  @NotNull
  public List<Category> getCategories() {
    return categories;
  }

  public void setCategories(@NotNull List<Category> categories) {
    this.categories = categories;
  }

  @NotNull
  public boolean isVerified() {
    return verified;
  }

  public void setVerified(@NotNull boolean verified) {
    this.verified = verified;
  }

  @NotNull
  public Stats getStats() {
    return stats;
  }

  public void setStats(@NotNull Stats stats) {
    this.stats = stats;
  }

  @Nullable
  public String getUrl() {
    return url;
  }

  public void setUrl(@Nullable String url) {
    this.url = url;
  }

  @Nullable
  public Menu getMenu() {
    return menu;
  }

  public void setMenu(@Nullable Menu menu) {
    this.menu = menu;
  }

  @Override
  public String toString() {
    return "Venue: " + getName();
  }
}
