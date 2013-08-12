package no.mesan.android.demo.task;

import no.mesan.android.demo.R;
import no.mesan.android.demo.model.dto.TwitterDto;
import no.mesan.android.demo.model.util.TwitterUtil;
import android.app.ProgressDialog;
import android.content.Context;

public class SearchForNewTweetsTask extends AbstractAsyncTask<String, Void, TwitterDto> {
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
		getCallback().handleResult(result);
		super.onPostExecute(result);
	}

}
