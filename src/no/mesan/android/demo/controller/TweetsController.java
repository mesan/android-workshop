package no.mesan.android.demo.controller;

import java.util.ArrayList;

import no.mesan.android.demo.model.dto.TweetDTO;
import no.mesan.android.demo.model.dto.TwitterDTO;
import no.mesan.android.demo.model.util.TwitterUtil;
import no.mesan.android.demo.view.adapter.TweetsControllerAdapter;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class TweetsController extends Activity {

	private ListView lstTweets;
	private String keyword;
	private TwitterDTO twitterDTO;
	protected ArrayList<TweetDTO> tweetList;
	private final String URL = "http://www.twitter.com/";

	private TwitterUtil twitterUtil;
	private TweetsControllerAdapter tweetsControllerAdapter;

	private ProgressDialog progress;

	private Context context;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tweets_controller);

		context = this;

		keyword = getIntent().getStringExtra("keyword");

		initComponents();
		renderView();
		initListeners();
	}

	private void initComponents() {
		twitterUtil = new TwitterUtil(context);
		lstTweets = (ListView) findViewById(R.id.lstTweets);
		tweetList = new ArrayList<TweetDTO>();
	}

	private void renderView() {

		// Get tweets
		new SearchForNewTweetsTask().execute(keyword.toString());
	}

	private void initListeners() {
		lstTweets.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int index, long id) {
				Uri uri = Uri.parse(URL + tweetList.get(index).getProfileName());
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
		});
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
				tweetList = twitterDTO.getTweets();
				tweetsControllerAdapter = new TweetsControllerAdapter(context, tweetList);
				lstTweets.setAdapter(tweetsControllerAdapter);
			}

			progress.dismiss();

			super.onPostExecute(searchSuccess);
		}

	}
}
