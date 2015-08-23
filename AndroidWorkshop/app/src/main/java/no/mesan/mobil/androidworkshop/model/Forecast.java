package no.mesan.mobil.androidworkshop.model;

import java.util.List;

public class Forecast {

    private City city;

    private List<WeatherInfo> list;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<WeatherInfo> getList() {
        return list;
    }

    public void setList(List<WeatherInfo> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "list=" + list +
                '}';
    }
}
