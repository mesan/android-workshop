package no.mesan.android.demo.model.dto;

import android.graphics.drawable.Drawable;

public class FlickrDto {

	private String title;
	private String imageUrl;
	private Drawable image;
	private boolean loading;
	
	public FlickrDto(String title, String imageUrl) {
		this.title = title;
		this.imageUrl = imageUrl;
	}
	
	public String getTitle() {
		return title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public Drawable getImage() {
		return image;
	}

	public void setImage(Drawable image) {
		this.image = image;
	}	
	
	public boolean hasImage() {
		return this.image != null;
	}

	public boolean isLoading() {
		return loading;
	}

	public void setLoading(boolean loading) {
		this.loading = loading;
	}
	
	
	
}
