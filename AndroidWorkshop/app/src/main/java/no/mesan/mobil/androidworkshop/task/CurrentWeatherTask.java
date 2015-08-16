package no.mesan.mobil.androidworkshop.task;

import android.os.AsyncTask;
import no.mesan.mobil.androidworkshop.model.CurrentWeather;
import no.mesan.mobil.androidworkshop.restservice.BaseRestService;
import no.mesan.mobil.androidworkshop.restservice.CurrentWeatherRestService;

public class CurrentWeatherTask extends AsyncTask<Void, Void, CurrentWeather> {

    private ResponseListener<CurrentWeather> responseListener;

    public CurrentWeatherTask(ResponseListener<CurrentWeather> responseListener) {
        this.responseListener = responseListener;
    }

    @Override
    protected CurrentWeather doInBackground(Void... voids) {
        CurrentWeatherRestService currentWeatherRestService = BaseRestService.getCurrentWeatherRestService();
        return currentWeatherRestService.getCurrentWeather("Oslo");
    }

    @Override
    protected void onPostExecute(CurrentWeather currentWeather) {
        super.onPostExecute(currentWeather);

        if (currentWeather == null) {
            responseListener.error();
        }
        else {
            responseListener.success(currentWeather);
        }
    }
}
