package com.mesan.android.demo.model.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;

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
		return null;
	}
	
	public TwitterDTO parseJson(String json){
		TwitterDTO twitterDTO = null;
		
		try {

			twitterDTO = new TwitterDTO();
			
			JSONObject shipmentObject = new JSONObject(json);			
			JSONArray resultArray = shipmentObject.optJSONArray("results");			
			int resultSize = resultArray.length();
			
			ArrayList<TweetDTO> tweetList = null;
			TweetDTO tweetDTO = null;
			JSONObject tweet = null;
			
			for(int i = 0; i<resultSize; i++){
				tweetDTO = new TweetDTO();
				tweetList = new ArrayList<TweetDTO>();
				
				tweet = resultArray.optJSONObject(i);
				
				tweetDTO.setText(tweet.optString("text"));
				
				try {
					tweetDTO.setImageUrl(new URL(tweet.optString("profile_image_url")));
				} catch (MalformedURLException murlex) {
					Log.i(TwitterService.class.getSimpleName(), "unparseable url", murlex);
				}
				tweetList.add(tweetDTO);
			}
			
		} catch (JSONException e) {
			Log.e(TwitterService.class.getSimpleName(), e.getMessage());
		}
		
		return twitterDTO;
	}
}
