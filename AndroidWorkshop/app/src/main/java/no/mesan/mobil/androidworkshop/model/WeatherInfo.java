package no.mesan.mobil.androidworkshop.model;

import java.util.List;

public class WeatherInfo {
    private List<Weather> weather;

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "weather=" + weather +
                '}';
    }
}
