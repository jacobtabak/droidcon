package com.timehop.droidcon2014retrofitsample.data.foursquare.api;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;

public class FoursquareErrorHandler implements ErrorHandler {
  @Override
  public Throwable handleError(RetrofitError cause) {
    if (cause.getResponse() != null && cause.getSuccessType() == FoursquareResponse.class) {
      FoursquareResponse response = (FoursquareResponse) cause.getBodyAs(cause.getSuccessType());
      if (response.getMeta().getErrorDetail() != null) {
        return new FoursquareException(response.getMeta().getErrorDetail(), cause);
      }
    }
    return cause;
  }
}
