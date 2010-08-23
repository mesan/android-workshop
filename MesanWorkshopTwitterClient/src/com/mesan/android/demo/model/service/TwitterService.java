package com.mesan.android.demo.model.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

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

import com.mesan.android.demo.model.dto.TweetDTO;
import com.mesan.android.demo.model.dto.TwitterDTO;

public class TwitterService {

	public TwitterService(){
		
	}
	
	public TwitterDTO getTweetFromWeb(String keyword){
		
		// Execute the request
		HttpResponse response;
		HttpGet httpget = null;

		// Prepare a request object
		httpget = new HttpGet("http://search.twitter.com/search.json?result_type=recent&q=" + keyword);
		
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
	
	public TwitterDTO parseJson(String json, String keyword){
		TwitterDTO twitterDTO = null;
		
		try {

			twitterDTO = new TwitterDTO();
			
			JSONObject shipmentObject = new JSONObject(json);			
			JSONArray resultArray = shipmentObject.optJSONArray("results");			
			int resultSize = resultArray.length();
			
			ArrayList<TweetDTO> tweetList = new ArrayList<TweetDTO>();
			TweetDTO tweetDTO = null;
			JSONObject tweet = null;
			DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
			formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

			for(int i = 0; i<resultSize; i++){
				tweetDTO = new TweetDTO();
				
				tweet = resultArray.optJSONObject(i);
				
				tweetDTO.setText(tweet.optString("text"));
				tweetDTO.setProfileName(tweet.optString("from_user"));
				try {
					tweetDTO.setProfileUrl(new URL(tweet.optString("profile_image_url")));
				} catch (MalformedURLException murlex) {
					Log.i(TwitterService.class.getSimpleName(), "unparseable url", murlex);
				}
				
			    try {
					tweetDTO.setDate((Date)formatter.parse(tweet.optString("created_at")));
				} catch (ParseException pex) {
					Log.i(TwitterService.class.getSimpleName(), "unparseable date", pex);
				}
				
				tweetList.add(tweetDTO);
			}
			
			twitterDTO.setKeyword(keyword);
			twitterDTO.setTweets(tweetList);
			
		} catch (JSONException e) {
			Log.e(TwitterService.class.getSimpleName(), e.getMessage());
		}
		return twitterDTO;
	}
}
