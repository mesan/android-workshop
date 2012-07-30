package no.mesan.android.demo.task;

import no.mesan.android.demo.model.service.Request;
import android.graphics.drawable.Drawable;

public class DownloadImageTask extends AbstractAsyncTask<String, Void, Drawable> {

	@Override
	protected Drawable doInBackground(String... params) {
		return new Request().getImageFromWeb(params[0]);
	}

	@Override
	protected void onPostExecute(Drawable result) {
		getCallback().handleResult(result);
		super.onPostExecute(result);
	}
}
