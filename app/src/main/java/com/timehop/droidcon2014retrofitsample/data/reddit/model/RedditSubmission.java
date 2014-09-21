package com.timehop.droidcon2014retrofitsample.data.reddit.model;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

public class RedditSubmission extends RedditObject {
  String banned_by;
  String subreddit;
  boolean saved;
  String id;
  int gilded;
  String author;
  int score;
  String name;
  LocalDateTime created;
  String author_flair_text;
  DateTime created_utc;
  int ups;

  public String getBannedBy() {
    return banned_by;
  }

  public String getSubreddit() {
    return subreddit;
  }

  public boolean isSaved() {
    return saved;
  }

  public String getId() {
    return id;
  }

  public int getGilded() {
    return gilded;
  }

  public String getAuthor() {
    return author;
  }

  public int getScore() {
    return score;
  }

  public String getName() {
    return name;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public String getAuthorFlairText() {
    return author_flair_text;
  }

  public DateTime getCreatedUtc() {
    return created_utc;
  }

  public int getUps() {
    return ups;
  }
}
