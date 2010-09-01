package no.mesan.android.demo.model.service;

import java.io.IOException;
import java.util.ArrayList;

import no.mesan.android.demo.model.application.Application;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;


public class FlickrService {
	private Context context;
	
	public FlickrService(Context context){
		this.context = context;
	}

	private static final String FLICKR_SEARCH_API_URL = "http://api.flickr.com/services/rest/?method=flickr.photos.search&nojsoncallback=1&format=json&api_key=6b3b39e81d8f4b5f527250506e146d4b&sort=interestingness-asc&extras=url_s&per_page=10&tags=";

	public ArrayList<String> getImagesFromFlickr(String keyword) {

		keyword = keyword.replaceAll(" ", "+");
		
		if(Application.isNetworkAvailable(context)){
			// Execute the request
			HttpResponse response = Application
					.sendGetRequestForUrl(FLICKR_SEARCH_API_URL + keyword);

			StatusLine status = response.getStatusLine();

			if (status.getStatusCode() == 200) {
				try {
					return parseJson(EntityUtils.toString(response.getEntity()),
							keyword);
				} catch (ParseException pex) {
					Log.e(TwitterService.class.getSimpleName(), pex.getMessage(),
							pex);
				} catch (IOException ioex) {
					Log.e(TwitterService.class.getSimpleName(), ioex.getMessage(),
							ioex);
				}
			}
		}
		

		return null;
	}

	private ArrayList<String> parseJson(String json, String keyword) {
		ArrayList<String> urlList = new ArrayList<String>();

		try {

			JSONObject shipmentObject = new JSONObject(json);
			JSONObject photos = shipmentObject.optJSONObject("photos");
			JSONArray photoArray = photos.optJSONArray("photo");

			int photoArrSize = photoArray.length();

			JSONObject photo = null;

			for (int i = 0; i < photoArrSize; i++) {
				photo = photoArray.optJSONObject(i);

				urlList.add(photo.optString("url_s").replaceAll("\\\\", ""));
			}

		} catch (JSONException e) {
			Log.e(TwitterService.class.getSimpleName(), e.getMessage());
		}
		return urlList;
	}
}
