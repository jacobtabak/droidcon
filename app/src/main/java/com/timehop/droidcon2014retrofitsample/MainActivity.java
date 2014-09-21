package com.timehop.droidcon2014retrofitsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

  private EditText mLocationEditText;
  private EditText mSubredditEditText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mLocationEditText = (EditText) findViewById(R.id.main_venue_edittext);
    mSubredditEditText = (EditText) findViewById(R.id.main_subreddit_edittext);
    findViewById(R.id.main_subreddit_button).setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            onSubredditButtonClicked();
          }
        }
    );
    findViewById(R.id.main_venue_button).setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            onVenueButtonClicked();
          }
        }
    );
  }

  private void onVenueButtonClicked() {
    Intent intent = new Intent(this, VenueSearchActivity.class);
    String location = mLocationEditText.getText().toString();
    if (location.length() == 0) {
      location = mLocationEditText.getHint().toString();
    }
    intent.putExtra(VenueSearchActivity.EXTRA_LOCATION, location);
    startActivity(intent);
  }

  private void onSubredditButtonClicked() {
    Intent intent = new Intent(this, SubredditActivity.class);
    String subreddit = mSubredditEditText.getText().toString();
    if (subreddit.length() == 0) {
      subreddit = mSubredditEditText.getHint().toString();
    }
    intent.putExtra(SubredditActivity.EXTRA_SUBREDDIT, subreddit);
    startActivity(intent);
  }
}
