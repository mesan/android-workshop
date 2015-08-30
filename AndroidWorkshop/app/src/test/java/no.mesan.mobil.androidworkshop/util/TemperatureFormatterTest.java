package no.mesan.mobil.androidworkshop.util;

import junit.framework.Assert;

import org.junit.Test;

/* For useful information, visit
https://developer.android.com/training/testing/unit-testing/local-unit-tests.html
 */

public class TemperatureFormatterTest {

    @Test
    public void temperatureFormatter_PositiveTemperature() {
        String temperature = TemperatureFormatter.format(18);
        Assert.assertEquals("18.0" + ((char) 0x00B0) + "C", temperature);
    }

    @Test
    public void temperatureFormatter_NegativeTemperature() {
        String temperature = TemperatureFormatter.format(-15);
        Assert.assertEquals("-15.0" + ((char) 0x00B0) + "C", temperature);
    }

    @Test
    public void temperatureFormatter_ZeroTemperature() {
        String temperature = TemperatureFormatter.format(0);
        Assert.assertEquals("0.0" + ((char) 0x00B0) + "C", temperature);
    }

    @Test
    public void temperatureFormatter_TwoDecimalsRoundUp() {
        String temperature = TemperatureFormatter.format(12.68);
        Assert.assertEquals("12.7" + ((char) 0x00B0) + "C", temperature);
    }

    @Test
    public void temperatureFormatter_TwoDecimalsRoundDown() {
        String temperature = TemperatureFormatter.format(24.14);
        Assert.assertEquals("24.1" + ((char) 0x00B0) + "C", temperature);
    }
}