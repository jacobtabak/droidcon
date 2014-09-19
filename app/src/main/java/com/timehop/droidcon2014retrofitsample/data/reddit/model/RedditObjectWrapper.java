package com.timehop.droidcon2014retrofitsample.data.reddit.model;

import com.google.gson.JsonElement;
import com.timehop.droidcon2014retrofitsample.data.reddit.RedditType;

public class RedditObjectWrapper {
  RedditType kind;
  JsonElement data;

  public RedditType getKind() {
    return kind;
  }

  public JsonElement getData() {
    return data;
  }
}
