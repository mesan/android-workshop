package no.mesan.android.demo.controller;

import java.util.ArrayList;

import no.mesan.android.demo.model.util.FlickrUtil;
import no.mesan.android.demo.view.adapter.GalleryAdapter;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Gallery;

public class GalleryController extends Activity {

	private Context context;
	private String keyword;
	private Gallery glrFlickrImages;
	private ArrayList<Drawable> listOfFlickrImg;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.gallery_controller);

		context = this;

		keyword = getIntent().getStringExtra("keyword");

		initComponents();

		// Get flickr images
		new SearchForFlickrImagesTask().execute(keyword.toString());
	}

	private void initComponents() {
		glrFlickrImages = (Gallery) findViewById(R.id.glrFlickerImages);
		listOfFlickrImg = new ArrayList<Drawable>();
	}

	private class SearchForFlickrImagesTask extends AsyncTask<String, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(context, "Kontakter Flickr", "søker etter bilder",
					true, true);
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(String... params) {

			FlickrUtil flickrUtil = new FlickrUtil(context);
			listOfFlickrImg = flickrUtil.getFlickrImagesByKeywordFromWeb(params[0]);

			if (listOfFlickrImg != null) {
				return true;
			}
			return false;
		}

		@Override
		protected void onPostExecute(Boolean isSuccess) {
			if (isSuccess) {
				glrFlickrImages.setAdapter(new GalleryAdapter(context, listOfFlickrImg));
			}
			progressDialog.dismiss();
			super.onPostExecute(isSuccess);
		}
	}
}
