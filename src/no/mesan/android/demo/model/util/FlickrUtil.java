package no.mesan.android.demo.model.util;

import java.util.List;

import no.mesan.android.demo.model.dto.FlickrDto;
import no.mesan.android.demo.model.service.FlickrService;
import android.content.Context;

/**
 * Utility class to abstract communications with Flickr API
 * 
 * @author Thomas
 * 
 */
public class FlickrUtil {

	private Context context;

	public FlickrUtil(Context context) {
		this.context = context;
	}

	/**
	 * Get the top 10 Flickr images by a given keyword.
	 * 
	 * @param keyword
	 *            - String
	 * @return ArrayList<Drawable> - list of images
	 */
	public List<FlickrDto> getFlickrImagesByKeywordFromWeb(String keyword) {
		return new FlickrService(context).getImagesFromFlickr(keyword);
	}
}
