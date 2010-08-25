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
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.mesan.android.demo.model.application.Application;
import com.mesan.android.demo.model.dto.TweetDTO;
import com.mesan.android.demo.model.dto.TwitterDTO;

public class TwitterService {
	
	private static final String TWITTER_SEARCH_URL = "http://search.twitter.com/search.json?result_type=recent&q=";
	private static final String TWITTER_TRENDING_TOPICS = "http://api.twitter.com/1/trends.json";

	public TwitterService(){
		
	}
	
	public TwitterDTO getTweetFromWeb(String keyword){

		// Execute the request
		HttpResponse response = Application.sendGetRequestForUrl(TWITTER_SEARCH_URL + keyword);

		StatusLine status = response.getStatusLine();
		
		if (status.getStatusCode() == 200) {
			try {
				return parseTwitterJson(EntityUtils.toString(response.getEntity()), keyword);
			} catch (IOException ioex) {
				Log.e(TwitterService.class.getSimpleName(), ioex.getMessage(), ioex);
			}
		}
		
		return null;
	}
	
	public ArrayList<String> searchForTrendingTopics(){
		HttpResponse response = Application.sendGetRequestForUrl(TWITTER_TRENDING_TOPICS);
		StatusLine status = response.getStatusLine();
		
		if (status.getStatusCode() == 200) {
			try {
				return parseTrendingTopicsJson(EntityUtils.toString(response.getEntity()));
			} catch (IOException ioex) {
				Log.e(TwitterService.class.getSimpleName(), ioex.getMessage(), ioex);
			}
		}
		
		return null;
	}
	
	public TwitterDTO parseTwitterJson(String json, String keyword){
		TwitterDTO twitterDTO = null;
		
		try {

			twitterDTO = new TwitterDTO();
			
			JSONObject twitterObject = new JSONObject(json);			
			JSONArray resultArray = twitterObject.optJSONArray("results");			
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
			Log.e(TwitterService.class.getSimpleName(), e.getMessage(), e);
		}
		return twitterDTO;
	}
	
	public ArrayList<String> parseTrendingTopicsJson(String json){
		
		try {
			
			JSONObject trendingTopics = new JSONObject(json);
			JSONArray topicsArray = trendingTopics.optJSONArray("trends");	
			
			int numOfTopics = topicsArray.length();
			
			ArrayList<String> topicsList = new ArrayList<String>();
			
			for(int i = 0; i<numOfTopics; i++){
				JSONObject topic = topicsArray.optJSONObject(i);
				topicsList.add(topic.optString("name"));
			}
			
			return topicsList;
		} catch (JSONException e) {
			Log.e(TwitterService.class.getSimpleName(), e.getMessage(), e);
		}			
		
		return null;
	}
}
