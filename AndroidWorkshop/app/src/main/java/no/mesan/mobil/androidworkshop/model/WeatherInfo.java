package no.mesan.mobil.androidworkshop.model;

import org.joda.time.DateTime;

import java.util.List;

import no.mesan.mobil.androidworkshop.restservice.DateTimeDeserializer;

public class WeatherInfo {
    private DateTime dt;
    private WeatherMain main;
    private Temp temp;
    private List<Weather> weather;

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public WeatherMain getMain() {
        return main;
    }

    public void setMain(WeatherMain main) {
        this.main = main;
    }

    public DateTime getDt() {
        return dt;
    }

    public void setDt(DateTime dt) {
        this.dt = dt;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "dt=" + dt +
                ", main=" + main +
                ", temp=" + temp +
                ", weather=" + weather +
                '}';
    }
}
