package no.mesan.android.demo.ui;

import java.util.ArrayList;
import java.util.List;

import no.mesan.android.demo.R;
import no.mesan.android.demo.model.dto.FlickrDto;
import no.mesan.android.demo.model.util.FlickrUtil;

import no.mesan.android.demo.view.GalleryAdapter;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class GalleryFragment extends Fragment {

	private Context context;
	private View viewer;	
	private String keyword;
	
	private GridView gridViewFlickrImages;
	private List<FlickrDto> flickrImageList;
	private GalleryAdapter galleryAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		viewer = inflater.inflate(R.layout.fragment_gallery, container, false);
		context = viewer.getContext();
		
		keyword = getActivity().getIntent().getStringExtra(MainActivity.INTENT_KEYWORD);
		
		initGui();
		return viewer;
	}
	
	private void initGui() {
		gridViewFlickrImages = (GridView) viewer.findViewById(R.id.gridViewFlickrImages);
		flickrImageList = new ArrayList<FlickrDto>();
		galleryAdapter = new GalleryAdapter(context, flickrImageList);
		gridViewFlickrImages.setAdapter(galleryAdapter);
		
		// Get images
		new SearchForFlickrImagesTask(context).execute(keyword.toString());
	}
	
	private void updateGallery(List<FlickrDto> result) {
		flickrImageList.clear();
		if (result != null) {
			flickrImageList.addAll(result);
		}
		galleryAdapter.notifyDataSetChanged();
	}
	
	private class SearchForFlickrImagesTask extends AsyncTask<String, Void, List<FlickrDto>> {

		private ProgressDialog progressDialog;
		private Context context;
		

		public SearchForFlickrImagesTask(Context context) {
			this.context = context;
		}

		@Override
		protected void onPreExecute() {
			String title = context.getString(R.string.task_progress_title_gallery);
			String body = context.getString(R.string.task_progress_body_gallery);
			progressDialog = ProgressDialog.show(context, title, body, true, true);
			super.onPreExecute();
		}

		@Override
		protected List<FlickrDto> doInBackground(String... params) {
			return new FlickrUtil(context).getFlickrImagesByKeywordFromWeb(params[0]);
		}

		@Override
		protected void onPostExecute(List<FlickrDto> result) {
			progressDialog.dismiss();
			updateGallery(result);
			super.onPostExecute(result);
		}
	}

}
