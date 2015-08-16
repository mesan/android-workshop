package no.mesan.mobil.androidworkshop.model;

public class CurrentWeather {
    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    private Weather weather;

    @Override
    public String toString() {
        return "CurrentWeather{" +
                "weather=" + weather +
                '}';
    }
}
