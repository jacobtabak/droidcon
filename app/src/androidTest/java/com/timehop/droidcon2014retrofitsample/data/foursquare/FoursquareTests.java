package com.timehop.droidcon2014retrofitsample.data.foursquare;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.timehop.droidcon2014retrofitsample.data.SynchronousExecutor;
import com.timehop.droidcon2014retrofitsample.data.foursquare.api.FoursquareCredentials;
import com.timehop.droidcon2014retrofitsample.data.foursquare.api.FoursquareException;
import com.timehop.droidcon2014retrofitsample.data.foursquare.api.FoursquareResponse;
import com.timehop.droidcon2014retrofitsample.data.foursquare.legacy.VenueSearchTask;
import com.timehop.droidcon2014retrofitsample.data.foursquare.model.Venue;

import org.apache.http.message.BasicNameValuePair;

import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class FoursquareTests extends ApplicationTestCase<Application> {
  private static final String TAG = "RedditTests";

  public FoursquareTests() {
    super(Application.class);
  }

  /**
   * Test using our legacy HttpUrlConnection async task
   */
  public void testHttpUrlConnectionVenueSearch() {
    BasicNameValuePair clientId =
        new BasicNameValuePair("client_id", FoursquareCredentials.CLIENT_ID);
    BasicNameValuePair clientSecret =
        new BasicNameValuePair("client_secret", FoursquareCredentials.CLIENT_SECRET);
    BasicNameValuePair version = new BasicNameValuePair("v", "20140914");
    BasicNameValuePair near = new BasicNameValuePair("near", "New York, New York");
    List<Venue> venues = new VenueSearchTask()
        .doInBackground(clientId, clientSecret, version, near);
    assertNotNull(venues);
    assertNotSame(0, venues.size());
    assertNotNull(venues.get(0));
    assertNotNull(venues.get(0).getName());
  }

  /**
   * Test using with synchronous Retrofit method
   */
  public void testRetrofitVenueSearch() {
    FoursquareService service = FoursquareService.Implementation.get();
    try {
      FoursquareResponse response = service.searchVenues("New York, New York");
      assertNotNull(response);
      assertNotNull(response.getResponse());
      assertNotNull(response.getResponse().getVenues());
      assertNotSame(0, response.getResponse().getVenues());
      assertNotNull(response.getResponse().getVenues().get(0).getName());
      response.getResponse().getVenues();
      Log.d(TAG, "Got response");
    } catch (FoursquareException e) {
      Log.d(TAG, e.getMessage(), e);
    }
  }

  /**
   * Test using async Retrofit method
   */
  public void testAsyncRetrofitVenueSearch() {
    FoursquareService service = FoursquareService.Implementation.getBuilder()
        .setExecutors(new SynchronousExecutor(), new SynchronousExecutor())
        .build()
        .create(FoursquareService.class);

    service.searchVenues("New York, New York", new Callback<FoursquareResponse>() {
      @Override
      public void success(FoursquareResponse foursquareResponse, Response response) {
        assertNotNull(foursquareResponse);
        assertNotNull(foursquareResponse.getResponse());
        assertNotNull(foursquareResponse.getResponse().getVenues());
        assertNotSame(0, foursquareResponse.getResponse().getVenues().size());
      }

      @Override
      public void failure(RetrofitError error) {
        fail("Failure should not occur - have you set up your API keys?");
      }
    });
  }

  /**
   * Test that our error handler works correctly
   */
  public void testErrorHandler() {
    FoursquareService service = FoursquareService.Implementation.getBuilder()
        .setRequestInterceptor(new RequestInterceptor() {
          @Override
          public void intercept(RequestFacade request) {
            // don't pass credentials, force an authError
          }
        })
        .setExecutors(new SynchronousExecutor(), new SynchronousExecutor())
        .build()
        .create(FoursquareService.class);

    service.searchVenues("New York, New York", new Callback<FoursquareResponse>() {
      @Override
      public void success(FoursquareResponse foursquareResponse, Response response) {
        fail("Request should not have succeeded");
      }

      @Override
      public void failure(RetrofitError error) {
        try {
          throw error.getCause();
        } catch (FoursquareException e) {
          assertNotNull(e.getMessage());
          assertTrue(e.getMessage().contains("https://developer.foursquare.com/docs/oauth.html"));
          assertTrue(e.getCause() instanceof RetrofitError);
          RetrofitError retrofitError = (RetrofitError) e.getCause();
          assertNotNull(retrofitError.getResponse());
          assertEquals(400, retrofitError.getResponse().getStatus());
        } catch (Throwable e) {
          fail("Foursquare exception should have been thrown");
        }
      }
    });
  }
}
