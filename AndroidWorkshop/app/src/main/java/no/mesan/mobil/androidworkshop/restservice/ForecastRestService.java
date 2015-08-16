package no.mesan.mobil.androidworkshop.restservice;

import no.mesan.mobil.androidworkshop.model.Forecast;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ForecastRestService {
    @GET("/forecast?units=metric")
    Forecast getFiveDayForecast(@Query("q") String location);

    @GET("/forecast/daily?units=metric")
    Forecast get16DayForecast(@Query("q") String location);
}
