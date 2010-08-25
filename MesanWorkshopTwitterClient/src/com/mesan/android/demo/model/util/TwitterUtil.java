package com.mesan.android.demo.model.util;

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

	public TwitterDTO getTweet(String keyword, boolean searchWeb){
				
		if(searchWeb){
			TwitterService twitterService = new TwitterService();
			FlickrService flickrService = new FlickrService();
			
			
			TwitterDTO twitterDTO = twitterService.getTweetFromWeb(keyword);			
			twitterDTO.setFlickrImages(flickrService.getImagesFromFlickr(keyword));
			twitterDAO.setTweet(twitterDTO);
		}
		
		return twitterDAO.getTweet(keyword);
	}
	
	public ArrayList<String> getTrendingTopics(){
		TwitterService twitterService = new TwitterService();
		return twitterService.searchForTrendingTopics();
	}
}
