package no.mesan.mobil.androidworkshop.task;

import android.os.AsyncTask;

import java.util.List;

import no.mesan.mobil.androidworkshop.model.FiveDayForecast;
import no.mesan.mobil.androidworkshop.model.WeatherInfo;
import no.mesan.mobil.androidworkshop.restservice.BaseRestService;
import no.mesan.mobil.androidworkshop.restservice.FiveDayForecastRestService;

public class FiveDayForecastTask extends AsyncTask<String, Void, FiveDayForecast> {

    private ResponseListener<FiveDayForecast> responseListener;

    public FiveDayForecastTask(ResponseListener<FiveDayForecast> responseListener) {
        this.responseListener = responseListener;
    }

    @Override
    protected FiveDayForecast doInBackground(String... location) {
        FiveDayForecastRestService fiveDayForecastRestService = BaseRestService.getFiveDayForecastRestService();
        return fiveDayForecastRestService.getFiveDayForecast(location[0]);
    }

    @Override
    protected void onPostExecute(FiveDayForecast weatherInfoList) {
        super.onPostExecute(weatherInfoList);
        if (weatherInfoList == null) {
            responseListener.error();
        }
        else {
            responseListener.success(weatherInfoList);
        }
    }
}
