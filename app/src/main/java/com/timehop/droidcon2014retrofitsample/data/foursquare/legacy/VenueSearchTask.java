package com.timehop.droidcon2014retrofitsample.data.foursquare.legacy;

import android.os.AsyncTask;
import android.util.Log;

import com.timehop.droidcon2014retrofitsample.data.foursquare.model.Category;
import com.timehop.droidcon2014retrofitsample.data.foursquare.model.Contact;
import com.timehop.droidcon2014retrofitsample.data.foursquare.api.FoursquareResponse;
import com.timehop.droidcon2014retrofitsample.data.foursquare.model.Icon;
import com.timehop.droidcon2014retrofitsample.data.foursquare.model.Location;
import com.timehop.droidcon2014retrofitsample.data.foursquare.model.Menu;
import com.timehop.droidcon2014retrofitsample.data.foursquare.model.Stats;
import com.timehop.droidcon2014retrofitsample.data.foursquare.model.Venue;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class VenueSearchTask extends AsyncTask<NameValuePair, Void, List<Venue>> {
  public static final String TAG = "VenueSearchTask";

  @Override
  public List<Venue> doInBackground(NameValuePair... params) {
    try {
      String endpoint = "https://api.foursquare.com/v2/venues/search";
      String query = buildQuery(params);
      URL url = new URL(endpoint + query);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      String response = readStream(con.getInputStream());
      List<Venue> venues = parseJson(response);
      return venues;
    } catch (IOException e) {
      Log.e(TAG, "Error retrieving venues", e);
    } catch (JSONException e) {
      Log.e(TAG, "Error parsing venues", e);
    }
    return null;
  }

  private String buildQuery(NameValuePair[] params) throws UnsupportedEncodingException {
    StringBuilder queryBuilder = new StringBuilder();
    for (NameValuePair param : params) {
      String value = param.getValue();
      value = URLEncoder.encode(String.valueOf(value), "UTF-8");
      queryBuilder.append(queryBuilder.length() > 0 ? '&' : '?');
      queryBuilder.append(param.getName()).append('=').append(value);
    }
    return queryBuilder.toString();
  }

  private String readStream(InputStream in) throws IOException {
    BufferedReader reader = null;
    StringBuilder responseBuilder = new StringBuilder();
    try {
      reader = new BufferedReader(new InputStreamReader(in));
      String line;
      while ((line = reader.readLine()) != null) {
        responseBuilder.append(line);
      }
      return responseBuilder.toString();
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private List<Venue> parseJson(String response) throws JSONException {
    List<Venue> venues = new ArrayList<Venue>();
    JSONObject responseJson = new JSONObject(response);
    JSONObject innerResponseJson = responseJson.getJSONObject(FoursquareResponse.FIELD_RESPONSE);
    JSONArray venuesJson = innerResponseJson.getJSONArray(Venue.FIELD_VENUES);
    for (int i = 0; i < venuesJson.length(); i++) {
      JSONObject venueJson = venuesJson.getJSONObject(i);
      Venue venue = new Venue();
      venue.setId(venueJson.getString(Venue.FIELD_ID));
      venue.setName(venueJson.getString(Venue.FIELD_NAME));

      JSONObject contactJson = venueJson.getJSONObject(Venue.FIELD_CONTACT);
      Contact contact = new Contact();
      contact.setTwitter(contactJson.optString(Contact.FIELD_TWITTER));
      contact.setPhone(contactJson.optString(Contact.FIELD_PHONE));
      contact.setFormattedPhone(contactJson.optString(Contact.FIELD_FORMATTED_PHONE));
      venue.setContact(contact);

      JSONObject locationJson = venueJson.getJSONObject(Venue.FIELD_LOCATION);
      Location location = new Location();
      location.setAddress(locationJson.optString(Location.FIELD_ADDRESS));
      location.setCrossStreet(locationJson.optString(Location.FIELD_CROSS_STREET));
      location.setCity(locationJson.optString(Location.FIELD_CITY));
      location.setState(locationJson.optString(Location.FIELD_STATE));
      location.setPostalCode(locationJson.optString(Location.FIELD_POSTAL_CODE));
      location.setCountry(locationJson.optString(Location.FIELD_COUNTRY));
      location.setLat(locationJson.optDouble(Location.FIELD_LAT));
      location.setLng(locationJson.optDouble(Location.FIELD_LNG));
      location.setDistance(locationJson.optDouble(Location.FIELD_DISTANCE));
      venue.setLocation(location);

      JSONArray categoriesJson = venueJson.getJSONArray(Venue.FIELD_CATEGORIES);
      List<Category> categories = new ArrayList<Category>(categoriesJson.length());
      for (int j = 0; j < categoriesJson.length(); j++) {
        JSONObject categoryJson = categoriesJson.getJSONObject(j);
        Category category = new Category();
        category.setId(categoryJson.getString(Category.FIELD_ID));
        category.setName(categoryJson.getString(Category.FIELD_NAME));
        category.setPluralName(categoryJson.getString(Category.FIELD_PLURAL_NAME));
        category.setShortName(categoryJson.getString(Category.FIELD_SHORT_NAME));

        JSONObject iconJson = categoryJson.getJSONObject(Category.FIELD_ICON);
        Icon icon = new Icon();
        icon.setPrefix(iconJson.getString(Icon.FIELD_PREFIX));
        icon.setSuffix(iconJson.getString(Icon.FIELD_SUFFIX));
        category.setIcon(icon);

        categories.add(category);
      }
      venue.setCategories(categories);

      venue.setVerified(venueJson.getBoolean(Venue.FIELD_VERIFIED));

      JSONObject statsJson = venueJson.getJSONObject(Venue.FIELD_STATS);
      Stats stats = new Stats();
      stats.setCheckinsCount(statsJson.getInt(Stats.FIELD_CHECKINS_COUNT));
      stats.setUsersCount(statsJson.getInt(Stats.FIELD_USERS_COUNT));
      stats.setTipCount(statsJson.getInt(Stats.FIELD_TIP_COUNT));
      venue.setStats(stats);

      venue.setUrl(venueJson.optString(Venue.FIELD_URL));

      JSONObject menuJson = venueJson.optJSONObject(Venue.FIELD_MENU);
      if (menuJson != null) {
        Menu menu = new Menu();
        menu.setUrl(menuJson.getString(Menu.FIELD_URL));
        menu.setMobileUrl(menuJson.getString(Menu.FIELD_MOBILE_URL));
        venue.setMenu(menu);
      }

      venues.add(venue);
    }
    return venues;
  }
}
