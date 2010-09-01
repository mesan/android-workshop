package no.mesan.android.demo.model.util;

import java.util.ArrayList;

import no.mesan.android.demo.model.application.Application;
import no.mesan.android.demo.model.service.FlickrService;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class FlickrUtil {

	public FlickrUtil(){
		
	}
	
	public ArrayList<Drawable> getFlickrUrlsByKeywordFromWeb(String keyword){
		ArrayList<Drawable> flickrList = null;
		
		try{
			FlickrService flickrService = new FlickrService();
			ArrayList<String> urls = flickrService.getImagesFromFlickr(keyword);
			int urlsLength = urls.size();
			flickrList = new ArrayList<Drawable>();
			
			for (int i = 0; i < urlsLength; i++) {
				flickrList.add(Application.getImageFromWeb(urls.get(i)));
			}
		} catch(Exception e){
			Log.d(FlickrUtil.class.getSimpleName(), e.getMessage(), e);
		}
		return flickrList;
	}
}
