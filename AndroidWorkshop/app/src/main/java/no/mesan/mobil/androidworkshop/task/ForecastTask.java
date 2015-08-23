package no.mesan.mobil.androidworkshop.task;

import android.os.AsyncTask;

import no.mesan.mobil.androidworkshop.model.Forecast;
import no.mesan.mobil.androidworkshop.model.ForecastType;
import no.mesan.mobil.androidworkshop.restservice.BaseRestService;
import no.mesan.mobil.androidworkshop.restservice.ForecastRestService;

public class ForecastTask extends AsyncTask<String, Void, Forecast> {

    private ResponseListener<Forecast> responseListener;
    private ForecastType forecastType;

    public ForecastTask(ResponseListener<Forecast> responseListener, ForecastType forecastType) {
        this.responseListener = responseListener;
        this.forecastType = forecastType;
    }

    @Override
    protected Forecast doInBackground(String... location) {
        ForecastRestService forecastRestService = BaseRestService.getFiveDayForecastRestService();
        if (forecastType.ordinal() == ForecastType.LONG.ordinal()) {
            return forecastRestService.get16DayForecast(location[0]);
        }
        return forecastRestService.getFiveDayForecast(location[0]);
    }

    @Override
    protected void onPostExecute(Forecast weatherInfoList) {
        super.onPostExecute(weatherInfoList);
        if (weatherInfoList == null) {
            responseListener.error();
        }
        else {
            responseListener.success(weatherInfoList);
        }
    }
}
