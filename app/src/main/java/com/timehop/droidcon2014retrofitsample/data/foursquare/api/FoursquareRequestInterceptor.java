package com.timehop.droidcon2014retrofitsample.data.foursquare.api;

import retrofit.RequestInterceptor;

public class FoursquareRequestInterceptor implements RequestInterceptor {
  @Override
  public void intercept(RequestFacade request) {
    request.addQueryParam("client_id", FoursquareCredentials.CLIENT_ID);
    request.addQueryParam("client_secret", FoursquareCredentials.CLIENT_SECRET);
    request.addQueryParam("v", "20141914");
  }
}
