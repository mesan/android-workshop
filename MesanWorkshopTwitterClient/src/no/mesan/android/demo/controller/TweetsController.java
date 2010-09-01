package no.mesan.android.demo.controller;

import java.util.ArrayList;

import no.mesan.android.demo.model.dto.TwitterDTO;
import no.mesan.android.demo.model.util.FlickrUtil;
import no.mesan.android.demo.model.util.TwitterUtil;
import no.mesan.android.demo.view.adapter.GalleryAdapter;
import no.mesan.android.demo.view.adapter.TweetsControllerAdapter;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Gallery;
import android.widget.ListView;

public class TweetsController extends Activity {

	private Gallery glrFlickrImages;
	private ListView lstTweets;
	private String keyword;
	private TwitterDTO twitterDTO;

	private TwitterUtil twitterUtil;
	private TweetsControllerAdapter tweetsControllerAdapter;

	private ProgressDialog progress;

	private Context context;
	private ArrayList<Drawable> listOfFlickrImg;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tweets_controller);

		context = this;

		keyword = getIntent().getStringExtra("keyword");

		initComponents();
		renderView();

	}

	private void initComponents() {
		twitterUtil = new TwitterUtil(context);
		lstTweets = (ListView) findViewById(R.id.lstTweets);
		glrFlickrImages = (Gallery) findViewById(R.id.glrFlickerImages);
		listOfFlickrImg = new ArrayList<Drawable>();
	}

	private void renderView() {

		// Get tweets
		new SearchForNewTweetsTask().execute(keyword.toString());

		// Get flickr images
		new SearchForFlickrImagesTask().execute(keyword.toString());
	}

	private class SearchForFlickrImagesTask extends
			AsyncTask<String, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(String... params) {

			FlickrUtil flickrUtil = new FlickrUtil(context);
			listOfFlickrImg = flickrUtil
					.getFlickrUrlsByKeywordFromWeb(params[0]);

			if (listOfFlickrImg != null) {
				return true;
			}

			return false;
		}

		@Override
		protected void onPostExecute(Boolean isSuccess) {
			super.onPostExecute(isSuccess);
			if (isSuccess) {
				glrFlickrImages.setAdapter(new GalleryAdapter(context,
						listOfFlickrImg));
			}

		}
	}

	private class SearchForNewTweetsTask extends
			AsyncTask<String, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			progress = ProgressDialog.show(context, "Kontakter Twitter",
					"søker etter tweets", true, true);
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
				tweetsControllerAdapter = new TweetsControllerAdapter(context,
						twitterDTO.getTweets());
				lstTweets.setAdapter(tweetsControllerAdapter);
			}

			progress.dismiss();

			super.onPostExecute(searchSuccess);
		}

	}
}
