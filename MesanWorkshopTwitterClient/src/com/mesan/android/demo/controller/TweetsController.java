package com.mesan.android.demo.controller;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.mesan.android.demo.controller.adapter.TweetsControllerAdapter;
import com.mesan.android.demo.model.dto.TwitterDTO;
import com.mesan.android.demo.model.util.TwitterUtil;

public class TweetsController extends Activity {

	private ListView lstTweets;
	private String keyword;
	private TwitterDTO twitterDTO;

	private TwitterUtil twitterUtil;
	private TweetsControllerAdapter tweetsControllerAdapter;

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
		lstTweets = (ListView) findViewById(R.id.lstTweets);

	}

	public void renderView() {
		tweetsControllerAdapter = new TweetsControllerAdapter(context,
				twitterDTO.getTweets());
		lstTweets.setAdapter(tweetsControllerAdapter);
	}

}
