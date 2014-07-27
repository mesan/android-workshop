package no.mesan.android.demo.ui;

import java.util.ArrayList;

import no.mesan.android.demo.R;
import no.mesan.android.demo.model.dto.TweetDto;
import no.mesan.android.demo.model.dto.TwitterDto;
import no.mesan.android.demo.model.util.TwitterUtil;
import no.mesan.android.demo.view.TweetsAdapter;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class TweetsFragment extends Fragment {

	private ListView lstTweets;
	private String keyword;
	protected ArrayList<TweetDto> tweetList;
	private static final String URL = "http://www.twitter.com/";

	private TweetsAdapter tweetsAdapter;

	private Context context;
	private View viewer;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		viewer = inflater.inflate(R.layout.fragment_tweets, container, false);
		context = viewer.getContext();
		
		// Oppgave 3.3
		
		initGui();
		initListeners();
		
		return viewer;
	}
	
	private void initGui() {
		// Oppgave 4.1
		
		// Oppgave 4.3
		
		// Oppgave 4.2
			
	}
	
	private void initListeners() {
		lstTweets.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int index, long id) {
				// Oppgave 4.8
			}
		});
	}
	
	private void updateTweets(TwitterDto result) {
		if(result != null) {
			tweetList.clear();
			tweetList.addAll(result.getTweets());
			tweetsAdapter.notifyDataSetChanged();
		}
	}
	
	private class SearchForNewTweetsTask extends AsyncTask<String, Void, TwitterDto> {
		private ProgressDialog progressDialog;
		private Context context;
		
		public SearchForNewTweetsTask(Context context) {
			this.context = context;
		}
		
		@Override
		protected void onPreExecute() {

			String title = context.getString(R.string.task_progress_title_tweets);
			String body = context.getString(R.string.task_progress_body_tweets);
			progressDialog = ProgressDialog.show(context, title, body,
					true, true);
			super.onPreExecute();
		}

		@Override
		protected TwitterDto doInBackground(String... params) {
			return new TwitterUtil(context).getTwitterDTO(params[0]);
		}

		@Override
		protected void onPostExecute(TwitterDto result) {
			progressDialog.dismiss();
			updateTweets(result);
			super.onPostExecute(result);
		}

	}

}
