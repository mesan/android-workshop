package com.mesan.android.demo.model.util;

import java.util.ArrayList;

import android.content.Context;

import com.mesan.android.demo.model.data.TwitterDAO;
import com.mesan.android.demo.model.dto.TwitterDTO;

public class TwitterUtil {

	private TwitterDAO twitterDAO;
	
	public TwitterUtil(Context context){
		twitterDAO = new TwitterDAO(context);
	}
	
	public ArrayList<TwitterDTO> getTweets(){
		
		
		return null;
	}
	
	public void searchForTweets(String keyword){
		
	}
}
