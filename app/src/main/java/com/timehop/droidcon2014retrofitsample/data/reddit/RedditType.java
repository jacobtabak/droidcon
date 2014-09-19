package com.timehop.droidcon2014retrofitsample.data.reddit;

import com.timehop.droidcon2014retrofitsample.data.reddit.model.RedditComment;
import com.timehop.droidcon2014retrofitsample.data.reddit.model.RedditLink;
import com.timehop.droidcon2014retrofitsample.data.reddit.model.RedditListing;
import com.timehop.droidcon2014retrofitsample.data.reddit.model.RedditMore;

public enum RedditType {
  t1(RedditComment.class),
  t3(RedditLink.class),
  Listing(RedditListing.class),
  more(RedditMore.class);

  private final Class mCls;

  RedditType(Class cls) {
    mCls = cls;
  }

  public Class getDerivedClass() {
    return mCls;
  }
}
