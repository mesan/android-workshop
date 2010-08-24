package com.mesan.android.demo.model.dto;

import java.net.URL;
import java.util.ArrayList;

public class TwitterDTO {
	private String keyword;
	private ArrayList<TweetDTO> tweets;
	private ArrayList<URL> flickrImages;

	public TwitterDTO() {
	}
	
	public TwitterDTO(String keyword) {
		setKeyword(keyword);
	}
	
	public TwitterDTO(String keyword, ArrayList<TweetDTO> tweets, ArrayList<URL> flickrImages) {
		setKeyword(keyword);
		setTweets(tweets);
		setFlickrImages(flickrImages);
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public ArrayList<TweetDTO> getTweets() {
		return tweets;
	}

	public void setTweets(ArrayList<TweetDTO> tweets) {
		this.tweets = tweets;
	}

	public ArrayList<URL> getFlickrImages() {
		return flickrImages;
	}

	public void setFlickrImages(ArrayList<URL> flickrImages) {
		this.flickrImages = flickrImages;
	}
	
	

}
