package com.timehop.droidcon2014retrofitsample.data.foursquare.model;

import com.google.gson.annotations.SerializedName;

public class Stats {
  public static final String FIELD_CHECKINS_COUNT = "checkinsCount";
  public static final String FIELD_USERS_COUNT = "usersCount";
  public static final String FIELD_TIP_COUNT = "tipCount";
  @SerializedName(FIELD_CHECKINS_COUNT) private int checkinsCount;
  @SerializedName(FIELD_USERS_COUNT) private int usersCount;
  @SerializedName(FIELD_TIP_COUNT) private int tipCount;

  public int getCheckinsCount() {
    return checkinsCount;
  }

  public void setCheckinsCount(int checkinsCount) {
    this.checkinsCount = checkinsCount;
  }

  public int getUsersCount() {
    return usersCount;
  }

  public void setUsersCount(int usersCount) {
    this.usersCount = usersCount;
  }

  public int getTipCount() {
    return tipCount;
  }

  public void setTipCount(int tipCount) {
    this.tipCount = tipCount;
  }
}
