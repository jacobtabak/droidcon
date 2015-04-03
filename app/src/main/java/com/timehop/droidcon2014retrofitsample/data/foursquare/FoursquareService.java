package com.timehop.droidcon2014retrofitsample.data.foursquare;

import com.timehop.droidcon2014retrofitsample.data.foursquare.api.FoursquareErrorHandler;
import com.timehop.droidcon2014retrofitsample.data.foursquare.api.FoursquareException;
import com.timehop.droidcon2014retrofitsample.data.foursquare.api.FoursquareRequestInterceptor;
import com.timehop.droidcon2014retrofitsample.data.foursquare.api.FoursquareResponse;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

public interface FoursquareService {

  @GET("/venues/search")
  FoursquareResponse searchVenues(@Query("near") String location) throws FoursquareException;

  @GET("/venues/search")
  void searchVenues(
      @Query("near") String location,
      Callback<FoursquareResponse> callback);

  class Implementation {
    public static FoursquareService get() {
      return getBuilder()
          .build()
          .create(FoursquareService.class);
    }

    static RestAdapter.Builder getBuilder() {
      return new RestAdapter.Builder()
          .setEndpoint("https://api.foursquare.com/v2")
          .setRequestInterceptor(new FoursquareRequestInterceptor())
          .setErrorHandler(new FoursquareErrorHandler());
    }
  }
}