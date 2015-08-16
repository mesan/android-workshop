package no.mesan.mobil.androidworkshop.model;

/**
 * Created by Thomas on 16.08.2015.
 */
public class WeatherMain {
    private double temp;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "WeatherMain{" +
                "temp=" + temp +
                '}';
    }
}
