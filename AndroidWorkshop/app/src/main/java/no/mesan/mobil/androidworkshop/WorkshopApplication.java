package no.mesan.mobil.androidworkshop;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by Thomas on 16.08.2015.
 */
public class WorkshopApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(getApplicationContext());
    }
}
