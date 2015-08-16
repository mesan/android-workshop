package no.mesan.mobil.androidworkshop.restservice;

import no.mesan.mobil.androidworkshop.model.WeatherInfo;
import retrofit.http.GET;
import retrofit.http.Query;

public interface CurrentWeatherRestService {

    @GET("/weather?units=metric")
    WeatherInfo getCurrentWeather(@Query("q") String location);
}
