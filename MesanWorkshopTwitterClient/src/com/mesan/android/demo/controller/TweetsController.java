package com.mesan.android.demo.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Gallery;
import android.widget.ListView;

import com.mesan.android.demo.controller.adapter.GalleryAdapter;
import com.mesan.android.demo.controller.adapter.TweetsControllerAdapter;
import com.mesan.android.demo.model.dto.TwitterDTO;
import com.mesan.android.demo.model.util.TwitterUtil;

public class TweetsController extends Activity {

	private Gallery glrFlickrImages;
	private ListView lstTweets;
	private String keyword;
	private TwitterDTO twitterDTO;

	private TwitterUtil twitterUtil;
	private TweetsControllerAdapter tweetsControllerAdapter;

	private ProgressDialog progress;

	private Context context;

	private ArrayList<String> urls;
	private ArrayList<Drawable> listOfFlickrImg;

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
		glrFlickrImages = (Gallery) findViewById(R.id.glrFlickerImages);
		listOfFlickrImg = new ArrayList<Drawable>();

		new SearchForNewTweetsTask().execute(keyword.toString());
	}

	private void renderView() {

		if (twitterDTO != null) {
			urls = twitterDTO.getFlickrImages();
			tweetsControllerAdapter = new TweetsControllerAdapter(context, twitterDTO.getTweets());
			lstTweets.setAdapter(tweetsControllerAdapter);
			int urlsSize = urls.size();
			String[] urlArr = new String[urlsSize];

			for (int i = 0; i < urlsSize; i++) {
				urlArr[i] = urls.get(i);
			}
			new ConvertUrlToDrawable().execute(urlArr);
		}
	}

	private class ConvertUrlToDrawable extends AsyncTask<String, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Progressbar
		}

		@Override
		protected Boolean doInBackground(String... params) {
			try {
				for (int i = 0; i < params.length; i++) {
					URL url = new URL(params[i]);
					InputStream is = new BufferedInputStream(url.openStream());
					listOfFlickrImg.add(Drawable.createFromStream(is, "src"));
				}
				return true;

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			glrFlickrImages.setAdapter(new GalleryAdapter(context, listOfFlickrImg));

		}
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
