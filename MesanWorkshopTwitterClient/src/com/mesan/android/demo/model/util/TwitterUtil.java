package com.mesan.android.demo.model.util;

import java.net.URL;
import java.util.ArrayList;

import android.content.Context;

import com.mesan.android.demo.model.dto.TwitterDTO;
import com.mesan.android.demo.model.persistence.TwitterDAO;
import com.mesan.android.demo.model.service.FlickrService;
import com.mesan.android.demo.model.service.TwitterService;

public class TwitterUtil {

	private TwitterDAO twitterDAO;
	
	public TwitterUtil(Context context){
		twitterDAO = new TwitterDAO(context);
	}
	
	public ArrayList<TwitterDTO> getTweets(){
		return twitterDAO.getTweets();
	}
	
	public TwitterDTO getTweet(String keyword){
		return twitterDAO.getTweet(keyword);
	}
	
	public TwitterDTO searchForTweet(String keyword, boolean searchWeb){
		
		if(searchWeb){
			TwitterService twitterService = new TwitterService();
			FlickrService flickrService = new FlickrService();
			
			TwitterDTO twitterDTO = twitterService.getTweetFromWeb(keyword);
			ArrayList<URL> flickrImages = flickrService.getImagesFromFlickr(keyword);
			twitterDTO.setFlickrImages(flickrImages);
			
			twitterDAO.setTweet(twitterDTO);
		}
		
		return twitterDAO.getTweet(keyword);
	}
}
