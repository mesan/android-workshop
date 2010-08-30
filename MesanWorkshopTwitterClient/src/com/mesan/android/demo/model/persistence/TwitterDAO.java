package com.mesan.android.demo.model.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.Configuration;
import com.db4o.query.Predicate;
import com.db4o.query.Query;
import com.mesan.android.demo.model.dto.TwitterDTO;

public class TwitterDAO {

	private Context context;
	private static ObjectContainer oc = null;
	private static final String PRIMARY_KEY = "keyword";

	public TwitterDAO(Context context) {
		this.context = context;
	}

	public ObjectContainer db() {
		try {
			if (oc == null || oc.ext().isClosed()) {
				oc = Db4o.openFile(dbConfig(), db4oDBFullPath(context));
			}
			return oc;
		} catch (Exception e) {
			Log.e(TwitterDAO.class.getName(), e.toString());
			return null;
		}
	}

	private Configuration dbConfig() {
		Configuration c = Db4o.newConfiguration();
		c.objectClass(TwitterDTO.class).objectField(PRIMARY_KEY).indexed(true);
		c.objectClass(TwitterDTO.class).updateDepth(3);
		c.objectClass(TwitterDTO.class).minimumActivationDepth(3);
		c.objectClass(TwitterDTO.class).cascadeOnDelete(true);
		return c;
	}

	private String db4oDBFullPath(Context ctx) {
		return ctx.getDir("data", 0) + "/" + "browsemap.db4o";
	}

	/**
	 * Close database connection
	 */
	public void close() {
		if (oc != null)
			oc.close();
	}

	public void setTweet(TwitterDTO twitterDTO) {
		String keyword = twitterDTO.getKeyword();
		TwitterDTO tempTweet = getTwitterDTO(keyword);

		if (tempTweet == null) {
			tempTweet = new TwitterDTO(keyword);
		}
		tempTweet.setTweets(twitterDTO.getTweets());
		tempTweet.setFlickrImages(twitterDTO.getFlickrImages());

		db().store(tempTweet);
		db().commit();
		close();
	}

	public TwitterDTO getTwitterDTO(final String keyword) {
		
		List<TwitterDTO> result = db().query(new Predicate<TwitterDTO>() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean match(TwitterDTO twitterDTO) {
				return twitterDTO.getKeyword().equals(keyword);
			}
		}); 
		
		if(result != null && result.size() > 0){
			return result.get(0);
		}
		return null;
	}

	public ArrayList<TwitterDTO> getTweets() {
		ArrayList<TwitterDTO> ret = new ArrayList<TwitterDTO>();
		ObjectSet<TwitterDTO> result = getAllTweets();
		while (result.hasNext())
			ret.add((TwitterDTO) result.next());
		
		Collections.sort(ret);
		return ret;
	}

	@SuppressWarnings("unchecked")
	private ObjectSet<TwitterDTO> getAllTweets() {
		Query query = db().query();
		query.constrain(TwitterDTO.class);
		query.descend(PRIMARY_KEY).orderAscending();
		return query.execute();
	}

	public void deleteTweet(String keyword) {
		
		// Search by name
		TwitterDTO twitterDTO = getTwitterDTO(keyword);
		
		// Delete object
		if (twitterDTO != null) {
			db().delete(twitterDTO);
			db().commit();
		}
	}

	public int tweetCount() {
		return getAllTweets().size();
	}
}
