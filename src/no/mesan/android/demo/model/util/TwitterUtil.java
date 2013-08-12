package no.mesan.android.demo.model.util;

import java.util.ArrayList;

import no.mesan.android.demo.model.dto.TwitterDto;
import no.mesan.android.demo.model.service.TwitterService;
import android.content.Context;

/**
 * Utility class to abstract communications with Twitter API and the DB
 * 
 * @author Thomas
 * 
 */
public class TwitterUtil {

//	private TwitterDao twitterDao;
	private Context context;

	public TwitterUtil(Context context) {
//		twitterDao = new TwitterDao(context);
		this.context = context;
	}

//	public ArrayList<TwitterDto> getAllTwitterDTOs() {
//		return twitterDao.getTweets();
//	}

	/**
	 * Use Twitter Search API to get 15 latest tweets regarding given keyword
	 * 
	 * @param keyword
	 *            - String
	 * @return TwitterDto - containing all information about tweets from Twitter
	 */
	public TwitterDto getTwitterDTO(String keyword) {

		TwitterService twitterService = new TwitterService(context);
		TwitterDto twitterDto = twitterService.getTweetFromWeb(keyword);

		return twitterDto;
	}

	/**
	 * Use Twitter Trending Topics API to get the top 10 topics at the moment
	 * 
	 * @return ArrayList<String> - containing all trending topics, max 10
	 */
	public ArrayList<String> getTrendingTopics() {
		TwitterService twitterService = new TwitterService(context);
		return twitterService.searchForTrendingTopics();
	}
}