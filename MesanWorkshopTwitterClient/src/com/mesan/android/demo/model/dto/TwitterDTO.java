package com.mesan.android.demo.model.dto;

import java.util.ArrayList;

public class TwitterDTO {
	private String keyword;
	private ArrayList<TweetDTO> tweets;

	public TwitterDTO() {
	}
	
	public TwitterDTO(String keyword) {
		setKeyword(keyword);
	}
	
	public TwitterDTO(String keyword, ArrayList<TweetDTO> tweets) {
		setKeyword(keyword);
		setTweets(tweets);
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
	
	

}
