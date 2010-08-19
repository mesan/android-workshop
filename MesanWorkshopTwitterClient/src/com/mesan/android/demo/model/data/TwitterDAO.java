package com.mesan.android.demo.model.data;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.Configuration;
import com.db4o.query.Query;
import com.mesan.android.demo.model.dto.TwitterDTO;

public class TwitterDAO {

	private Context context;
	private static ObjectContainer oc = null;
	
	public TwitterDAO(Context context){
		this.context = context;
	}
    
    public ObjectContainer db(){
    	try {
    		if(oc == null || oc.ext().isClosed()){
    			oc = Db4o.openFile(dbConfig(), db4oDBFullPath(context));
    		}
    		return oc;
    	} catch (Exception e) {
        	Log.e(TwitterDAO.class.getName(), e.toString());
        	return null;
        }
    }
    
    private Configuration dbConfig(){    	
    	Configuration c = Db4o.newConfiguration();    	
    	c.objectClass(TwitterDTO.class).objectField("keyword").indexed(true); 
    	c.objectClass(TwitterDTO.class).updateDepth(6);
    	c.objectClass(TwitterDTO.class).minimumActivationDepth(6);
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
    	if(oc != null)
    		oc.close();
    }
    
    /*public void setBookmark(String name, String description){    	
    	TwitterDTO bookmark = getTweet(name);
    	
    	if(bookmark == null){
    		bookmark = new MapBookmark(name);
    	}
    	bookmark.setDescription(description);

    	db().store(bookmark);
    	db().commit();
    }
    
    public MapBookmark getBookmark(String name){
    	MapBookmark bookmark = new MapBookmark(name);
    	ObjectSet<MapBookmark> result = db().queryByExample(bookmark);
    	
    	if(result.hasNext()){
    		return (MapBookmark)result.next();
    	}
    	return null;
    }
    
    @SuppressWarnings("unchecked")
	public List<MapBookmark> getBookmarkList(){
    	ArrayList<MapBookmark> ret = new ArrayList<MapBookmark>();
        ObjectSet result = getBookmarks();
        while (result.hasNext())
        	ret.add((MapBookmark)result.next());
        return ret;
    }
    
    @SuppressWarnings("unchecked")
	private ObjectSet getBookmarks(){
    	Query query = db().query();
    	query.constrain(MapBookmark.class);
    	query.descend("name").orderAscending();
    	return query.execute();
    }

    public void deleteBookmark(String name) {
        //Search by name
    	MapBookmark bkm = getBookmark(name);
        //Delete object
    	if(bkm != null){
    		db().delete(bkm);
    		db().commit();
    	}
    }
    
    public int bookamrkCount(){
    	return getBookmarks().size();
    }*/
}
