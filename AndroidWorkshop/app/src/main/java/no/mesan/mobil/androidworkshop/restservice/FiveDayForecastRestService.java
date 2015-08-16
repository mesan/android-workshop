package no.mesan.mobil.androidworkshop.restservice;

import java.util.List;

import no.mesan.mobil.androidworkshop.model.WeatherInfo;
import retrofit.http.GET;
import retrofit.http.Query;

public interface FiveDayForecastRestService {
    @GET("/forecast?units=metric")
    List<WeatherInfo> getFiveDayForecast(@Query("q") String location);
}
