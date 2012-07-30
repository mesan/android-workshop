package no.mesan.android.demo.ui;

import java.util.ArrayList;
import java.util.List;

import no.mesan.android.demo.model.dto.FlickrDto;
import no.mesan.android.demo.task.SearchForFlickrImagesTask;
import no.mesan.android.demo.task.TaskResult;
import no.mesan.android.demo.view.GalleryAdapter;
import android.app.Fragment;
import android.content.Context;
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
	
	private TaskResult<List<FlickrDto>> searchForFlickrImagesResult = new TaskResult<List<FlickrDto>>() {
		
		@Override
		public void handleResult(List<FlickrDto> result) {
			flickrImageList.clear();
			flickrImageList.addAll(result);
			galleryAdapter.notifyDataSetChanged();
		}
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		viewer = inflater.inflate(R.layout.fragment_gallery, container, false);
		context = viewer.getContext();
		
		keyword = getActivity().getIntent().getStringExtra("keyword");
		
		initGui();
		return viewer;
	}

	private void initGui() {
		gridViewFlickrImages = (GridView) viewer.findViewById(R.id.gridViewFlickrImages);
		flickrImageList = new ArrayList<FlickrDto>();
		galleryAdapter = new GalleryAdapter(context, flickrImageList);
		gridViewFlickrImages.setAdapter(galleryAdapter);
		
		// Get images
		new SearchForFlickrImagesTask(context).executeWithCallback(searchForFlickrImagesResult, keyword.toString());
	}
}
