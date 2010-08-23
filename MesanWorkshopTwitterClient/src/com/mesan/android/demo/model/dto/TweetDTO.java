package com.mesan.android.demo.model.dto;

import java.net.URL;
import java.util.Date;

public class TweetDTO {

	private String text, profileName;
	private URL profileUrl;
	
	private Date date;

	public TweetDTO() {

	}

	public TweetDTO(String text, String profileName, URL profileUrl, Date date) {
		setText(text);
		setProfileName(profileName);
		setProfileUrl(profileUrl);
		setDate(date);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public URL getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(URL profileUrl) {
		this.profileUrl = profileUrl;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
}
