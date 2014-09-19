package com.timehop.droidcon2014retrofitsample.data.foursquare.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Nullable;

public class Location {
  public static final String FIELD_ADDRESS = "address";
  public static final String FIELD_CROSS_STREET = "crossStreet";
  public static final String FIELD_CITY = "city";
  public static final String FIELD_STATE = "state";
  public static final String FIELD_POSTAL_CODE = "postalCode";
  public static final String FIELD_COUNTRY = "country";
  public static final String FIELD_LAT = "lat";
  public static final String FIELD_LNG = "lng";
  public static final String FIELD_DISTANCE = "distance";

  @Nullable @SerializedName(FIELD_ADDRESS) private String address;
  @Nullable @SerializedName(FIELD_CROSS_STREET) private String crossStreet;
  @Nullable @SerializedName(FIELD_CITY) private String city;
  @Nullable @SerializedName(FIELD_STATE) private String state;
  @Nullable @SerializedName(FIELD_POSTAL_CODE) private String postalCode;
  @Nullable @SerializedName(FIELD_COUNTRY) private String country;
  @Nullable @SerializedName(FIELD_LAT) private Double lat;
  @Nullable @SerializedName(FIELD_LNG) private Double lng;
  @Nullable @SerializedName(FIELD_DISTANCE) private Double distance;

  @Nullable
  public String getAddress() {
    return address;
  }

  public void setAddress(@Nullable String address) {
    this.address = address;
  }

  @Nullable
  public String getCrossStreet() {
    return crossStreet;
  }

  public void setCrossStreet(@Nullable String crossStreet) {
    this.crossStreet = crossStreet;
  }

  @Nullable
  public String getCity() {
    return city;
  }

  public void setCity(@Nullable String city) {
    this.city = city;
  }

  @Nullable
  public String getState() {
    return state;
  }

  public void setState(@Nullable String state) {
    this.state = state;
  }

  @Nullable
  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(@Nullable String postalCode) {
    this.postalCode = postalCode;
  }

  @Nullable
  public String getCountry() {
    return country;
  }

  public void setCountry(@Nullable String country) {
    this.country = country;
  }

  @Nullable
  public Double getLat() {
    return lat;
  }

  public void setLat(@Nullable Double lat) {
    this.lat = lat;
  }

  @Nullable
  public Double getLng() {
    return lng;
  }

  public void setLng(@Nullable Double lng) {
    this.lng = lng;
  }

  @Nullable
  public Double getDistance() {
    return distance;
  }

  public void setDistance(@Nullable Double distance) {
    this.distance = distance;
  }
}
