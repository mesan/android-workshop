package com.mesan.android.demo.controller;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mesan.android.demo.controller.adapter.TweetsControllerAdapter;
import com.mesan.android.demo.model.application.Application;
import com.mesan.android.demo.model.dto.TwitterDTO;
import com.mesan.android.demo.model.util.TwitterUtil;

public class TweetsController extends Activity {

	private ListView lstTweets;
	private String keyword;
	private TwitterDTO twitterDTO;

	private TwitterUtil twitterUtil;
	private TweetsControllerAdapter tweetsControllerAdapter;

	private ProgressDialog progress;

	private Context context;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tweets_controller);

		context = this;
		initComponents();

	}

	private void initComponents() {
		twitterUtil = new TwitterUtil(context);
		keyword = getIntent().getStringExtra("keyword");
		lstTweets = (ListView) findViewById(R.id.lstTweets);
		new SearchForNewTweetsTask().execute(keyword.toString());
	}

	private void renderView() {
		tweetsControllerAdapter = new TweetsControllerAdapter(context, twitterDTO.getTweets());
		lstTweets.setAdapter(tweetsControllerAdapter);
	}

	private class SearchForNewTweetsTask extends AsyncTask<String, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			progress = ProgressDialog.show(context, "Kontakter Twitter", "søker etter tweets", true, true);
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(String... params) {
			twitterDTO = twitterUtil.getTwitterDTO(params[0], true);
			if (twitterDTO != null) {
				return true;
			}
			return false;
		}

		@Override
		protected void onPostExecute(Boolean searchSuccess) {
			if (searchSuccess) {
				renderView();
			}
			progress.dismiss();

			super.onPostExecute(searchSuccess);
		}

	}
}
