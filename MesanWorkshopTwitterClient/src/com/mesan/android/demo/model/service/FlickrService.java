package com.mesan.android.demo.model.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class FlickrService {

	private static final String FLICKR_SEARCH_API_URL = "http://api.flickr.com/services/rest/?method=flickr.photos.search&nojsoncallback=1&format=json&api_key=6b3b39e81d8f4b5f527250506e146d4b&sort=interestingness-asc&extras=url_s&per_page=10&tags=";

	public ArrayList<URL> getImagesFromFlickr(String keyword){
		
		// Execute the request
		HttpResponse response;
		HttpGet httpget = null;

		// Prepare a request object
		httpget = new HttpGet(FLICKR_SEARCH_API_URL + keyword);
		
		DefaultHttpClient client = new DefaultHttpClient();

		
		
		try {
			response = client.execute(httpget);
			
			// If the response does not enclose an entity, there is no need
			// to worry about connection release
			StatusLine status = response.getStatusLine();
			
			if (status.getStatusCode() == 200) {
				return parseJson(EntityUtils.toString(response.getEntity()), keyword);
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	

	private ArrayList<URL> parseJson(String json, String keyword){
		ArrayList<URL> urlList = new ArrayList<URL>();
		
		try {
			
			JSONObject shipmentObject = new JSONObject(json);						
			JSONObject photos = shipmentObject.optJSONObject("photos");
			JSONArray photoArray = photos.optJSONArray("photo");
			
			int photoArrSize = photoArray.length();
			
			JSONObject photo = null;

			for(int i = 0; i<photoArrSize; i++){
				photo = photoArray.optJSONObject(i);
				
				try {
					urlList.add(new URL(photo.optString("url_s").replaceAll("\\\\", "")));
				} catch (MalformedURLException murlex) {
					Log.i(FlickrService.class.getSimpleName(), "unparseable url", murlex);
				}
			}
			
		} catch (JSONException e) {
			Log.e(TwitterService.class.getSimpleName(), e.getMessage());
		}
		return urlList;
	}
}
