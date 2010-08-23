package com.mesan.android.demo.model.util;

import java.util.ArrayList;

import android.content.Context;

import com.mesan.android.demo.model.dto.TwitterDTO;
import com.mesan.android.demo.model.persistence.TwitterDAO;
import com.mesan.android.demo.model.service.TwitterService;

public class TwitterUtil {

	private TwitterDAO twitterDAO;
	
	public TwitterUtil(Context context){
		twitterDAO = new TwitterDAO(context);
	}
	
	public ArrayList<TwitterDTO> getTweets(){
		
		
		return null;
	}
	
	public TwitterDTO searchForTweet(String keyword, boolean searchWeb){
		
		TwitterService twitterService = new TwitterService();
		
		if(searchWeb){
			TwitterDTO twitterDTO = twitterService.getTweetFromWeb(keyword);
			twitterDAO.setTweet(twitterDTO);
		}
		
		return twitterDAO.getTweet(keyword);
	}
}
