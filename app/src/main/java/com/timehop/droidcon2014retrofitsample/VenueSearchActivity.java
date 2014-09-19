package com.timehop.droidcon2014retrofitsample;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.timehop.droidcon2014retrofitsample.data.foursquare.FoursquareService;
import com.timehop.droidcon2014retrofitsample.data.foursquare.api.FoursquareException;
import com.timehop.droidcon2014retrofitsample.data.foursquare.api.FoursquareResponse;
import com.timehop.droidcon2014retrofitsample.data.foursquare.model.Venue;
import com.timehop.droidcon2014retrofitsample.ui.VenueAdapter;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class VenueSearchActivity extends Activity {
  public static final String TAG = VenueSearchActivity.class.getSimpleName();
  public static final String EXTRA_LOCATION = "location";
  private ListView mListView;
  private VenueAdapter mAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    String location = getIntent().getStringExtra(EXTRA_LOCATION);
    setContentView(R.layout.activity_venue_search);

    mListView = (ListView) findViewById(android.R.id.list);
    mAdapter = new VenueAdapter(this);
    mListView.setAdapter(mAdapter);

    final ProgressDialog progressDialog = new ProgressDialog(this);
    progressDialog.setMessage("Loading venues...");
    progressDialog.setCancelable(false);
    progressDialog.show();

    FoursquareService.Implementation.get().searchVenues(location,
        new Callback<FoursquareResponse>() {
          @Override
          public void success(FoursquareResponse foursquareResponse, Response response) {
            progressDialog.dismiss();
            List<Venue> venues = foursquareResponse.getResponse().getVenues();
            onVenuesReceived(venues);
          }

          @Override
          public void failure(RetrofitError error) {
            Log.e(TAG, "Failed to load venues", error);
            progressDialog.dismiss();
            String message = "Loading failed :(";
            try {
              throw (error.getCause());
            } catch (FoursquareException e) {
              Log.e(TAG, "Venue request failed", e);
              // save the error message to display in a dialog
              message = e.getMessage();
            } catch (Throwable e) {
              Log.e(TAG, "Venue request failed", e);
            }
            new AlertDialog.Builder(VenueSearchActivity.this)
                .setMessage(message)
                .setCancelable(false)
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                    finish();
                  }
                })
                .show();
          }
        });
  }

  private void onVenuesReceived(List<Venue> venues) {
    for (Venue venue : venues) {
      mAdapter.add(venue);
    }
  }
}
