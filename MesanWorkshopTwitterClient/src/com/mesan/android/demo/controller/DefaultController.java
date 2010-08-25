package com.mesan.android.demo.controller;

import java.util.ArrayList;

import com.mesan.android.demo.model.dto.TweetDTO;
import com.mesan.android.demo.model.dto.TwitterDTO;
import com.mesan.android.demo.model.util.TwitterUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class DefaultController extends Activity {
	
	private EditText txtKeyword;
	private Button btnSearch;
	private ListView lstKeywords;
	
	private ArrayList<String> keywords;
	private TwitterUtil twitterUtil;
	
	private static final int SEARCH_FAILED = 0;
	private static final int SEARCH_SUCCESS = 1;
	
	private Context context;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);               
        
        context = this;
        
        initLayout();
        renderView();
        initListeners();
        
    }

	private void initLayout() {
		txtKeyword = (EditText) findViewById(R.id.txtKeyword);
		btnSearch = (Button) findViewById(R.id.btnSearch);
		lstKeywords = (ListView) findViewById(R.id.lstKeywords);
	}


	private void renderView() {
		keywords = new ArrayList<String>();
		twitterUtil = new TwitterUtil(context);
	}

	private void initListeners() {
		btnSearch.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				if(!"".equals(txtKeyword.getText().toString())) {
					searchForTweets(txtKeyword.getText().toString());
				}
			}
		});
	}
	
	private void searchForTweets(final String keyword){
		new Thread(new Runnable() {
			
			public void run() {
				Message msg = new Message();
				
				TwitterDTO twitterDTO = twitterUtil.getTweet(keyword, true);
				
				if(twitterDTO != null){

					ArrayList<TweetDTO> tweetList = twitterDTO.getTweets();
					
					for(TweetDTO tweetDTO : tweetList){
						keywords.add(tweetDTO.getText());
					}
					
					msg.what = SEARCH_SUCCESS;
				}else{
					msg.what = SEARCH_FAILED;
				}
				
				tweetHandler.sendMessage(msg);
			}
		}).start();
	}
	
	private Handler tweetHandler = new Handler(){
		
		@Override
		public void handleMessage(Message msg) {
			if(msg.what == SEARCH_SUCCESS){
				lstKeywords.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, keywords));
			}
			super.handleMessage(msg);
		}
	};
}