package com.mesan.android.demo.controller.widget;

import java.util.ArrayList;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import com.mesan.android.demo.controller.DefaultController;
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
		
		private String mostPopularWord = "";
		
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
				
				String topWordAsOfNow = trendingTopics.get(0);
				
				if(!mostPopularWord.equalsIgnoreCase(topWordAsOfNow)){
					sendNotification(topWordAsOfNow);
				}
				mostPopularWord = topWordAsOfNow;
				
				updateViews.setTextViewText(R.id.lblTrendingTopics, topics.toString());

			} else {
				updateViews.setTextViewText(R.id.lblTrendingTopics, context.getText(R.string.widget_error));
			}

			return updateViews;
		}
		
		private void sendNotification(String word){

			Context context = getApplicationContext();
			
			String ns = Context.NOTIFICATION_SERVICE;
			NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
			
			int icon = R.drawable.notification;
			CharSequence tickerText = context.getString(R.string.notification_title);
			long when = System.currentTimeMillis();
			Notification notification = new Notification(icon, tickerText, when);
			
			CharSequence contentTitle = context.getString(R.string.notification_title);
			CharSequence contentText = word + " " + context.getString(R.string.notification_content);
			
			Intent notificationIntent = new Intent(this, DefaultController.class);
			PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
			notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
			
			mNotificationManager.notify(1, notification);
		}

		@Override
		public IBinder onBind(Intent intent) {
			// We don't need to bind to this service
			return null;
		}
	}

}
