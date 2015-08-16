package no.mesan.mobil.androidworkshop.task;

import android.os.AsyncTask;
import no.mesan.mobil.androidworkshop.model.WeatherInfo;
import no.mesan.mobil.androidworkshop.restservice.BaseRestService;
import no.mesan.mobil.androidworkshop.restservice.CurrentWeatherRestService;

public class CurrentWeatherTask extends AsyncTask<String, Void, WeatherInfo> {

    private ResponseListener<WeatherInfo> responseListener;

    public CurrentWeatherTask(ResponseListener<WeatherInfo> responseListener) {
        this.responseListener = responseListener;
    }

    @Override
    protected WeatherInfo doInBackground(String... location) {
        CurrentWeatherRestService currentWeatherRestService = BaseRestService.getCurrentWeatherRestService();
        return currentWeatherRestService.getCurrentWeather(location[0]);
    }

    @Override
    protected void onPostExecute(WeatherInfo weatherInfo) {
        super.onPostExecute(weatherInfo);

        if (weatherInfo == null) {
            responseListener.error();
        }
        else {
            responseListener.success(weatherInfo);
        }
    }
}
