package no.mesan.mobil.androidworkshop.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Locale;

public class DateFormatter {

    public static String formatDate(DateTime dateTime) {
        dateTime = dateTime.plusMonths(2);
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd. MMMM yyyy").withLocale(new Locale("nb", "NO"));
        return dateTimeFormatter.print(dateTime);
    }
}
