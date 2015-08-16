package no.mesan.mobil.androidworkshop.util;

public class TemperatureFormatter {

    public static String format(double temperature) {
        return Math.round(temperature * 10.0) / 10.0 + "" + ((char) 0x00B0) + "C";
    }
}
