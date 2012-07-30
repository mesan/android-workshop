package no.mesan.android.demo.task;

import java.util.List;

import no.mesan.android.demo.model.dto.FlickrDto;
import no.mesan.android.demo.model.util.FlickrUtil;
import no.mesan.android.demo.ui.R;
import android.app.ProgressDialog;
import android.content.Context;

public class SearchForFlickrImagesTask extends AbstractAsyncTask<String, Void, List<FlickrDto>> {

	private ProgressDialog progressDialog;
	private Context context;

	public SearchForFlickrImagesTask(Context context) {
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		String title = context.getString(R.string.task_progress_title_gallery);
		String body = context.getString(R.string.task_progress_body_gallery);
		progressDialog = ProgressDialog.show(context, title, body, true, true);
		super.onPreExecute();
	}

	@Override
	protected List<FlickrDto> doInBackground(String... params) {
		return new FlickrUtil(context).getFlickrImagesByKeywordFromWeb(params[0]);
	}

	@Override
	protected void onPostExecute(List<FlickrDto> result) {
		progressDialog.dismiss();
		getCallback().handleResult(result);
		super.onPostExecute(result);
	}
}
