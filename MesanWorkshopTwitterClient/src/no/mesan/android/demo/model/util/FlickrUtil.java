package no.mesan.android.demo.model.util;

import java.util.ArrayList;

import no.mesan.android.demo.model.service.FlickrService;

public class FlickrUtil {

	public FlickrUtil(){
		
	}
	
	public ArrayList<String> getFlickrUrlsByKeywordFromWeb(String keyword){

		FlickrService flickrService = new FlickrService();
		return flickrService.getImagesFromFlickr(keyword);
	}
}
