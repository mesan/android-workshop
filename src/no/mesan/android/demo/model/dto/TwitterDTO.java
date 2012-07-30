package no.mesan.android.demo.model.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class TwitterDto implements Comparable<TwitterDto>, Serializable {
	private static final long serialVersionUID = 5723006892309338592L;
	private String keyword;
	private ArrayList<TweetDto> tweets;
	private Long timeInMillis;
	
	public TwitterDto() {
		setTimeInMillis();
	}
	
	public TwitterDto(String keyword) {
		setKeyword(keyword);
		setTimeInMillis();
	}
	
	public TwitterDto(String keyword, ArrayList<TweetDto> tweets) {
		setKeyword(keyword);
		setTweets(tweets);
		setTimeInMillis();
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public ArrayList<TweetDto> getTweets() {
		return tweets;
	}

	public void setTweets(ArrayList<TweetDto> tweets) {
		this.tweets = tweets;
	}

	public Long getTimeInMillis() {
		return timeInMillis;
	}

	public void setTimeInMillis() {
		timeInMillis = System.currentTimeMillis();
	}

	public int compareTo(TwitterDto another) {
		return (getTimeInMillis() < another.getTimeInMillis()) ? 1 : -1;
	}
	
	

}
