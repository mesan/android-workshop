package no.mesan.android.demo.ui.widget;

import java.util.List;

import no.mesan.android.demo.R;
import no.mesan.android.demo.task.SearchForTrendingTopicsTask;
import no.mesan.android.demo.task.TaskResult;
import no.mesan.android.demo.ui.MainActivity;
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


public class TwitterWidget extends AppWidgetProvider {
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		context.startService(new Intent(context, UpdateService.class));
	}
	
	public static class UpdateService extends Service {
		
		private Context context;
		
		private TaskResult<List<String>> trendingTopicsResult = new TaskResult<List<String>>() {
			
			@Override
			public void handleResult(List<String> result) {

				// Build the widget update for today
				RemoteViews updateViews = buildUpdate(result);

				// Push update for this widget to the home screen
				ComponentName thisWidget = new ComponentName(context, TwitterWidget.class);			
				AppWidgetManager manager = AppWidgetManager.getInstance(context);

				manager.updateAppWidget(thisWidget, updateViews);
			}
		};
		
		private String mostPopularWord = "";
		
		@Override
		public void onStart(Intent intent, int startId) {
			context = this;
			new SearchForTrendingTopicsTask(context).executeWithCallback(trendingTopicsResult);
		}

		/**
		 * Build updated widget content
		 * 
		 * @param context
		 * @return RemoteViews
		 */
		public RemoteViews buildUpdate(List<String> trendingTopics) {
			
			// Get the view defining the widget
			RemoteViews updateViews = new RemoteViews(context.getPackageName(), R.layout.widget);			
			
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

			// Get context
			Context context = getApplicationContext();
			
			// Get the NotificationManager
			NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

			// Setup
			long when = System.currentTimeMillis();
			CharSequence tickerText = context.getString(R.string.notification_title);
			CharSequence contentTitle = context.getString(R.string.notification_title);
			CharSequence contentText = word + " " + context.getString(R.string.notification_content);
			
			// Set what to do when notification is clicked
			Intent notificationIntent = new Intent(this, MainActivity.class);
			PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
			
			Notification notification = new Notification.Builder(context)
	         .setContentTitle(contentTitle)
	         .setContentText(contentText)
	         .setContentIntent(contentIntent)
	         .setSmallIcon(R.drawable.notification)
	         .setTicker(tickerText)
	         .setWhen(when)
	         .setAutoCancel(true)
	         .getNotification();
			
			// Notify
			notificationManager.notify(1, notification);
		}

		@Override
		public IBinder onBind(Intent intent) {
			// We don't need to bind to this service
			return null;
		}
	}
	
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		context.stopService(new Intent(context, UpdateService.class));
		super.onDeleted(context, appWidgetIds);
	}

}
