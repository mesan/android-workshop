package no.mesan.android.demo.model.util;

import java.util.ArrayList;

import no.mesan.android.demo.model.dto.TwitterDTO;
import no.mesan.android.demo.model.persistence.TwitterDAO;
import no.mesan.android.demo.model.service.FlickrService;
import no.mesan.android.demo.model.service.TwitterService;

import android.content.Context;


public class TwitterUtil {

	private TwitterDAO twitterDAO;
	
	public TwitterUtil(Context context){
		twitterDAO = new TwitterDAO(context);
	}
	
	public ArrayList<TwitterDTO> getAllTwitterDTOs(){
		return twitterDAO.getTweets();
	}

	public TwitterDTO getTwitterDTO(String keyword, boolean searchWeb){
				
		if(searchWeb){
			TwitterService twitterService = new TwitterService();
			FlickrService flickrService = new FlickrService();
			
			
			TwitterDTO twitterDTO = twitterService.getTweetFromWeb(keyword);		
			twitterDTO.setFlickrImages(flickrService.getImagesFromFlickr(keyword));
			twitterDAO.setTweet(twitterDTO);
		}
		
		return twitterDAO.getTwitterDTO(keyword);
	}
	
	public ArrayList<String> getTrendingTopics(){
		TwitterService twitterService = new TwitterService();
		return twitterService.searchForTrendingTopics();
	}
}
