package com.timehop.droidcon2014retrofitsample.data.reddit;

import com.timehop.droidcon2014retrofitsample.data.reddit.model.MockModelHelper;
import com.timehop.droidcon2014retrofitsample.data.reddit.model.RedditListing;
import com.timehop.droidcon2014retrofitsample.data.reddit.model.RedditResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.client.Header;
import retrofit.client.Response;
import retrofit.http.Path;

public class MockRedditService implements RedditService {
  @Override
  public List<RedditResponse<RedditListing>> getComments(String subreddit, String id) {
    return MockModelHelper.getMockCommentsResponse();
  }

  @Override
  public void getComments(String subreddit, String id,
                          Callback<List<RedditResponse<RedditListing>>> callback) {
    List<RedditResponse<RedditListing>> response = MockModelHelper.getMockCommentsResponse();
    callback.success(response,
        new Response("http://reddit.com/r/" + subreddit + "/comments/" + id + ".json",
            200, "OK", new ArrayList<Header>(), null));
  }

  @Override
  public RedditResponse<RedditListing> getSubreddit(@Path("subreddit") String subreddit) {
    throw new RuntimeException("Not implemented");
  }

  @Override
  public void getSubreddit(@Path("subreddit") String subreddit,
                           Callback<RedditResponse<RedditListing>> callback) {
    throw new RuntimeException("Not implemented");
  }
}
