package no.mesan.mobil.androidworkshop.model;

import java.util.List;

public class WeatherInfo {
    private List<Weather> weatherArray;

    public List<Weather> getWeatherArray() {
        return weatherArray;
    }

    public void setWeatherArray(List<Weather> weatherArray) {
        this.weatherArray = weatherArray;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "weatherArray=" + weatherArray +
                '}';
    }
}
