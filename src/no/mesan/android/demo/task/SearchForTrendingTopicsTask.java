package no.mesan.android.demo.task;

import java.util.List;

import android.content.Context;

import no.mesan.android.demo.model.util.TwitterUtil;

public class SearchForTrendingTopicsTask extends AbstractAsyncTask<Void, Void, List<String>> {
	private Context context;
	
	public SearchForTrendingTopicsTask(Context context) {
		this.context = context;
	}

	@Override
	protected List<String> doInBackground(Void... params) {
		return new TwitterUtil(context).getTrendingTopics();
	}	
	
	@Override
	protected void onPostExecute(List<String> result) {
		getCallback().handleResult(result);
		super.onPostExecute(result);
	}
}
