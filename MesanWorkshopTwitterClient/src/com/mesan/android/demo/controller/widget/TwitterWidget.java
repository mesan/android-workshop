package com.mesan.android.demo.controller.widget;

import java.util.ArrayList;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import com.mesan.android.demo.controller.R;
import com.mesan.android.demo.model.util.TwitterUtil;


public class TwitterWidget extends AppWidgetProvider {
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		context.startService(new Intent(context, UpdateService.class));
	}
	
	public static class UpdateService extends Service {
		
		@Override
		public void onStart(Intent intent, int startId) {
			
			// Build the widget update for today
			RemoteViews updateViews = buildUpdate(this);

			// Push update for this widget to the home screen
			ComponentName thisWidget = new ComponentName(this, TwitterWidget.class);			
			AppWidgetManager manager = AppWidgetManager.getInstance(this);

			manager.updateAppWidget(thisWidget, updateViews);
		}

		/**
		 * Build updated widget content
		 * 
		 * @param context
		 * @return RemoteViews
		 */
		public RemoteViews buildUpdate(Context context) {
			
			// Get the view defining the widget
			RemoteViews updateViews = new RemoteViews(context.getPackageName(), R.layout.widget);

			// Get post from web
			TwitterUtil twitterUtil = new TwitterUtil(context);			
			ArrayList<String> trendingTopics = twitterUtil.getTrendingTopics();	
			
			
			if (trendingTopics != null && trendingTopics.size() > 0) {
				
				int numOfTopics = trendingTopics.size();
				StringBuilder topics = new StringBuilder();
				
				for(int i = 0; i<numOfTopics; i++){
					
					topics.append(trendingTopics.get(i));
					
					if(i<numOfTopics-1){
						topics.append(", ");
					}
				}
				
				updateViews.setTextViewText(R.id.lblTrendingTopics, topics.toString());

			} else {
				updateViews.setTextViewText(R.id.lblTrendingTopics, context.getText(R.string.widget_error));
			}

			return updateViews;
		}

		@Override
		public IBinder onBind(Intent intent) {
			// We don't need to bind to this service
			return null;
		}
	}

}
