package com.timehop.droidcon2014retrofitsample.data.reddit;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.timehop.droidcon2014retrofitsample.data.SynchronousExecutor;
import com.timehop.droidcon2014retrofitsample.data.reddit.model.RedditComment;
import com.timehop.droidcon2014retrofitsample.data.reddit.model.RedditLink;
import com.timehop.droidcon2014retrofitsample.data.reddit.model.RedditListing;
import com.timehop.droidcon2014retrofitsample.data.reddit.model.RedditMore;
import com.timehop.droidcon2014retrofitsample.data.reddit.model.RedditObject;
import com.timehop.droidcon2014retrofitsample.data.reddit.model.RedditResponse;

import junit.framework.Assert;

import java.util.List;

import retrofit.Callback;
import retrofit.MockRestAdapter;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RedditTests extends ApplicationTestCase<Application> {
  private RedditService mService;

  public RedditTests() {
    super(Application.class);
  }

  /**
   * Sets up the Reddit service with a synchronous executor and full logging
   * @throws Exception
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    mService = RedditService.Implementation.getBuilder()
        .setExecutors(new SynchronousExecutor(), new SynchronousExecutor())
        .setLogLevel(RestAdapter.LogLevel.FULL)
        .build()
        .create(RedditService.class);
  }

  /**
   * Example of getting Reddit comments synchronously with Retrofit
   */
  public void testGetComments() {
    List<RedditResponse<RedditListing>> responses = mService.getComments("AskReddit", "2gp9iv");
    assertComments(responses);
  }

  /**
   * Example of getting Reddit comments asynchronously with Retrofit
   */
  public void testGetCommentsAsync() {
    mService.getComments("AskReddit", "2gp9iv", new Callback<List<RedditResponse<RedditListing>>>() {
      @Override
      public void success(List<RedditResponse<RedditListing>> responses, Response response) {
        assertComments(responses);
      }

      @Override
      public void failure(RetrofitError error) {
        // request failed, fail the test
        fail();
      }
    });
  }

  /**
   * Example demonstrating a mock client
   */
  public void testMockClient() {
    MockRedditService mockService = new MockRedditService();
    List<RedditResponse<RedditListing>> response = mockService.getComments("AskReddit", "2gp9iv");
    assertComments(response);
  }

  /**
   * Example demonstrating a mock client asynchronously
   */
  public void testMockClientAsync() {
    MockRedditService mockService = new MockRedditService();
    mockService.getComments("AskReddit", "2gp9iv", new Callback<List<RedditResponse<RedditListing>>>() {
      @Override
      public void success(List<RedditResponse<RedditListing>> redditResponses, Response response) {
        assertComments(redditResponses);
      }

      @Override
      public void failure(RetrofitError error) {
        fail("Should not fail");
      }
    });
  }

  /**
   * Example using a mock client with delay
   */
  public void testMockClientWithDelay() {
    RestAdapter restAdapter = RedditService.Implementation.getBuilder().build();
    MockRestAdapter mockRestAdapter = MockRestAdapter.from(restAdapter);
    mockRestAdapter.setDelay(2500);
    RedditService service = mockRestAdapter.create(RedditService.class, new MockRedditService());
    long start = System.currentTimeMillis();
    List<RedditResponse<RedditListing>> response = service.getComments("AskReddit", "2gp9iv");
    long end = System.currentTimeMillis();
    assertTrue(end > start + 2000);
    assertComments(response);
  }

  /**
   * helper function to assert comments response is valid
   */
  private void assertComments(List<RedditResponse<RedditListing>> response) {
    // very the first listing only has links
    for (RedditObject child : response.get(0).getData().getChildren()) {
      Assert.assertTrue(child instanceof RedditLink);
    }

    // verify the second listing only has comments or 'more's
    for (RedditObject child : response.get(1).getData().getChildren()) {
      Assert.assertTrue(child instanceof RedditComment || child instanceof RedditMore);
    }
  }
}
