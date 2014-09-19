package com.timehop.droidcon2014retrofitsample.data.reddit.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This is in the model package to access package-protected members of the models.
 */
public class MockModelHelper {
  public static RedditListing createMockLinkListing() {
    RedditListing linkListing = new RedditListing();
    linkListing.children = new ArrayList<>();
    linkListing.children.add(new RedditLink());
    return linkListing;
  }

  public static RedditListing createMockCommentsListing() {
    RedditListing commentListing = new RedditListing();
    commentListing.children = new ArrayList<>();
    commentListing.children.add(new RedditComment());
    return commentListing;
  }

  public static List<RedditResponse<RedditListing>> getMockCommentsResponse() {
    List <RedditResponse<RedditListing>> response = new ArrayList<>();
    response.add(new RedditResponse<>(MockModelHelper.createMockLinkListing()));
    response.add(new RedditResponse<>(MockModelHelper.createMockCommentsListing()));
    return response;
  }
}
