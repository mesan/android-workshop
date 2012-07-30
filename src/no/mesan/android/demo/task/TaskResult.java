package no.mesan.android.demo.task;

public interface TaskResult<T> {

	void handleResult(T result);
}
