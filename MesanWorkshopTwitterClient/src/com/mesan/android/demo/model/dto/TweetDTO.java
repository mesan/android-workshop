package com.mesan.android.demo.model.dto;

import java.net.URL;
import java.util.Date;

public class TweetDTO {

	private String text;
	private URL imageUrl;
	private Date date;

	public TweetDTO() {

	}

	public TweetDTO(String text, URL imageUrl, Date date) {
		setText(text);
		setImageUrl(imageUrl);
		setDate(date);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public URL getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(URL imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
