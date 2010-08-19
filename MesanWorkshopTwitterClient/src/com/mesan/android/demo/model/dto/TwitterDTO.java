package com.mesan.android.demo.model.dto;

import java.util.ArrayList;

public class TwitterDTO {
	private String keyword;
	private ArrayList<String> tweets;

	public TwitterDTO(String keyword, ArrayList<String> tweets) {
		setKeyword(keyword);
		setTweets(tweets);
	}

	public String getKeyword() {
		return keyword;
	}

	public ArrayList<String> getTweets() {
		return tweets;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setTweets(ArrayList<String> tweets) {
		this.tweets = tweets;
	}

}
