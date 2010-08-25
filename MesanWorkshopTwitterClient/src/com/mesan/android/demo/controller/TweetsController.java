package com.mesan.android.demo.controller;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mesan.android.demo.model.dto.TweetDTO;
import com.mesan.android.demo.model.dto.TwitterDTO;
import com.mesan.android.demo.model.util.TwitterUtil;

public class TweetsController extends Activity {

	private ListView lstTweets;
	private ArrayList<String> tweetStrings;
	private ArrayList<TweetDTO> tweets;
	private String keyword;
	private TwitterDTO twitterDTO;

	private TwitterUtil twitterUtil;

	private Context context;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tweets_controller);

		context = this;
		initComponents();
		renderView();

	}

	public void initComponents() {
		twitterUtil = new TwitterUtil(context);
		keyword = getIntent().getStringExtra("keyword");
		twitterDTO = twitterUtil.getTwitterDTO(keyword, false);
		tweetStrings = new ArrayList<String>();
		lstTweets = (ListView) findViewById(R.id.lstTweets);
	}

	public void renderView() {
		tweets = twitterDTO.getTweets();
		for (TweetDTO t : tweets) {
			tweetStrings.add(t.getText());
		}
		lstTweets.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, tweetStrings));
	}

}
