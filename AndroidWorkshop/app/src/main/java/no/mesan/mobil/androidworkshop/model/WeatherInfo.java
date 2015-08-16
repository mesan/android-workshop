package no.mesan.mobil.androidworkshop.model;

public class WeatherInfo {
    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    private Weather weather;

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "weather=" + weather +
                '}';
    }
}
