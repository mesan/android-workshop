package no.mesan.mobil.androidworkshop.task;

import android.os.AsyncTask;

import java.util.List;

import no.mesan.mobil.androidworkshop.model.WeatherInfo;
import no.mesan.mobil.androidworkshop.restservice.BaseRestService;
import no.mesan.mobil.androidworkshop.restservice.FiveDayForecastRestService;

public class FiveDayForecastTask extends AsyncTask<String, Void, List<WeatherInfo>> {

    private ResponseListener<List<WeatherInfo>> responseListener;

    public FiveDayForecastTask(ResponseListener<List<WeatherInfo>> responseListener) {
        this.responseListener = responseListener;
    }

    @Override
    protected List<WeatherInfo> doInBackground(String... location) {
        FiveDayForecastRestService fiveDayForecastRestService = BaseRestService.getFiveDayForecastRestService();
        return fiveDayForecastRestService.getFiveDayForecast(location[0]);
    }

    @Override
    protected void onPostExecute(List<WeatherInfo> weatherInfoList) {
        super.onPostExecute(weatherInfoList);
        if (weatherInfoList == null) {
            responseListener.error();
        }
        else {
            responseListener.success(weatherInfoList);
        }
    }
}
