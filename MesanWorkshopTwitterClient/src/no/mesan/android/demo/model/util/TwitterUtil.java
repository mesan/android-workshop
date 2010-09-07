package no.mesan.android.demo.model.util;

import java.util.ArrayList;

import no.mesan.android.demo.model.dto.TwitterDTO;
import no.mesan.android.demo.model.persistence.TwitterDAO;
import no.mesan.android.demo.model.service.TwitterService;
import android.content.Context;


public class TwitterUtil {

	private TwitterDAO twitterDAO;
	private Context context;
	
	public TwitterUtil(Context context){
		twitterDAO = new TwitterDAO(context);
		this.context = context;
	}
	
	public ArrayList<TwitterDTO> getAllTwitterDTOs(){
		return twitterDAO.getTweets();
	}

	public TwitterDTO getTwitterDTO(String keyword, boolean searchWeb){
				
		if(searchWeb){
			TwitterService twitterService = new TwitterService(context);			
			TwitterDTO twitterDTO = twitterService.getTweetFromWeb(keyword);		
			if(twitterDTO != null){				
				twitterDAO.saveTweet(twitterDTO);
			}
		}
		
		return twitterDAO.getTwitterDTO(keyword);
	}
	
	public ArrayList<String> getTrendingTopics(){
		TwitterService twitterService = new TwitterService(context);
		return twitterService.searchForTrendingTopics();
	}
}