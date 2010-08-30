package com.mesan.android.demo.model.dto;

import java.io.Serializable;
import java.util.Date;

public class TweetDTO implements Serializable {
	private static final long serialVersionUID = 2769808976839209522L;
	private String text, profileName;
	private String profileUrl;

	private Date date;

	public TweetDTO() {

	}

	public TweetDTO(String text, String profileName, String profileUrl,
			Date date) {
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

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
