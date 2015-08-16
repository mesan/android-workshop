package no.mesan.mobil.androidworkshop.restservice;

import no.mesan.mobil.androidworkshop.model.FiveDayForecast;
import retrofit.http.GET;
import retrofit.http.Query;

public interface FiveDayForecastRestService {
    @GET("/forecast?units=metric")
    FiveDayForecast getFiveDayForecast(@Query("q") String location);
}
