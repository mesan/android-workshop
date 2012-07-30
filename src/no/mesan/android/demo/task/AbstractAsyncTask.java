package no.mesan.android.demo.task;

import android.os.AsyncTask;

public abstract class AbstractAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

	private TaskResult<Result> callback;
	
	public AbstractAsyncTask<Params, Progress, Result> executeWithCallback(final TaskResult<Result> callback, final Params... params) {
		setTaskResult(callback);
		execute(params);
		return this;
	}
	
	public void setTaskResult(final TaskResult<Result> callback) {
		this.callback = callback;
	}

	public TaskResult<Result> getCallback() {
		return callback;
	}
}
