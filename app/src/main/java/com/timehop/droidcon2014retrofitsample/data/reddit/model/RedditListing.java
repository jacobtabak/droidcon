package com.timehop.droidcon2014retrofitsample.data.reddit.model;

import java.util.List;

public class RedditListing extends RedditObject {
  String modhash;
  String after;
  String before;
  List<RedditObject> children;

  public String getModhash() {
    return modhash;
  }

  public String getAfter() {
    return after;
  }

  public String getBefore() {
    return before;
  }

  public List<RedditObject> getChildren() {
    return children;
  }
}
