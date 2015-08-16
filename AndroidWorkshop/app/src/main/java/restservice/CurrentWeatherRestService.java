package restservice;

import model.CurrentWeather;
import retrofit.http.GET;
import retrofit.http.Query;

public interface CurrentWeatherRestService {

    @GET("/weather")
    CurrentWeather getCurrentWeather(@Query("q") String location);
}
