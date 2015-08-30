package no.mesan.mobil.androidworkshop.util;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.junit.Test;

/* For useful information, visit
https://developer.android.com/training/testing/unit-testing/local-unit-tests.html
 */

public class DateFormatterTest {

    @Test
    public void dateFormatter_FormatDate() throws Exception {
        String date = DateFormatter.formatDate(new DateTime(2015, 9, 1, 12, 0));
        Assert.assertEquals("01. september 2015", date);
    }

    @Test
    public void dateFormatter_FormatTime() throws Exception {
        String time = DateFormatter.formatTime(new DateTime(2015, 9, 1, 12, 0));
        Assert.assertEquals("12:00", time);
    }
}
