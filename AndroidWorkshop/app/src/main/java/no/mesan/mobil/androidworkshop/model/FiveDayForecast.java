package no.mesan.mobil.androidworkshop.model;

import java.util.List;

/**
 * Created by Thomas on 16.08.2015.
 */
public class FiveDayForecast {

    private List<WeatherInfo> list;

    public List<WeatherInfo> getList() {
        return list;
    }

    public void setList(List<WeatherInfo> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "FiveDayForecast{" +
                "list=" + list +
                '}';
    }
}
