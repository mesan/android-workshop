package no.mesan.android.demo.model.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import no.mesan.android.demo.model.dto.TwitterDto;

import android.content.Context;
import android.util.Log;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.Configuration;
import com.db4o.query.Predicate;
import com.db4o.query.Query;

/**
 * Persistence class for a TwitterDto. Using db4o as database
 * 
 * @author Thomas Pettersen
 * 
 */
public class TwitterDao {

	private Context context;
	private static ObjectContainer oc = null;
	private static final String PRIMARY_KEY = "keyword";

	public TwitterDao(Context context) {
		this.context = context;
	}

	public ObjectContainer db() {
		try {
			if (oc == null || oc.ext().isClosed()) {
				oc = Db4o.openFile(dbConfig(), db4oDBFullPath(context));
			}
			return oc;
		} catch (Exception e) {
			Log.e(TwitterDao.class.getName(), e.toString());
			return null;
		}
	}

	private Configuration dbConfig() {
		Configuration c = Db4o.newConfiguration();
		c.objectClass(TwitterDto.class).objectField(PRIMARY_KEY).indexed(true);
		c.objectClass(TwitterDto.class).updateDepth(6);
		c.objectClass(TwitterDto.class).minimumActivationDepth(6);
		c.objectClass(TwitterDto.class).cascadeOnDelete(true);
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

	/**
	 * Create and update method
	 * 
	 * @param twitterDto
	 */
	public void saveTweet(TwitterDto twitterDto) {
		String keyword = twitterDto.getKeyword();
		TwitterDto tempTweet = getTwitterDTO(keyword);

		if (tempTweet == null) {
			tempTweet = new TwitterDto(keyword);
		}
		tempTweet.setTweets(twitterDto.getTweets());

		db().store(tempTweet);
		db().commit();
		close();
	}

	/**
	 * Get a TwitterDto by the keyword
	 * 
	 * @param keyword
	 * @return TwitterDto
	 */
	public TwitterDto getTwitterDTO(final String keyword) {

		List<TwitterDto> result = db().query(new Predicate<TwitterDto>() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean match(TwitterDto twitterDto) {
				return twitterDto.getKeyword().equals(keyword);
			}
		});

		if (result != null && result.size() > 0) {
			return result.get(0);
		}
		return null;
	}

	/**
	 * Get all twitterDTOs in the Database
	 * 
	 * @return ArrayList<TwitterDto>
	 */
	public ArrayList<TwitterDto> getTweets() {
		ArrayList<TwitterDto> ret = new ArrayList<TwitterDto>();
		ObjectSet<TwitterDto> result = getAllTweets();

		if (result != null) {
			while (result.hasNext())
				ret.add((TwitterDto) result.next());

			Collections.sort(ret);

		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	private ObjectSet<TwitterDto> getAllTweets() {
		ObjectContainer db = db();

		if (db != null) {
			Query query = db().query();
			query.constrain(TwitterDto.class);
			query.descend(PRIMARY_KEY).orderAscending();
			return query.execute();
		}
		return null;
	}

	/**
	 * Remove a twitterDTO from DB
	 * 
	 * @param keyword
	 */
	public void deleteTweet(String keyword) {

		// Search by name
		TwitterDto twitterDto = getTwitterDTO(keyword);

		// Delete object
		if (twitterDto != null) {
			db().delete(twitterDto);
			db().commit();
		}
	}

	public int tweetCount() {
		return getAllTweets().size();
	}
}
