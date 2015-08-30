package no.mesan.mobil.androidworkshop.view.forecast;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import no.mesan.mobil.androidworkshop.R;
import no.mesan.mobil.androidworkshop.model.ForecastType;
import no.mesan.mobil.androidworkshop.model.WeatherInfo;
import no.mesan.mobil.androidworkshop.task.CurrentWeatherTask;
import no.mesan.mobil.androidworkshop.task.ResponseListener;
import no.mesan.mobil.androidworkshop.view.BaseActivity;

public class ForecastActivity extends BaseActivity {

    public static final String LOCATION = "location";
    public static final String FORECAST_TYPE = "forecastType";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        // As we're using a Toolbar, we should retrieve it and set it
        // to be our ActionBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setTitle(title);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        // Oppgave 6
        if (getIntent() != null) {
            String location = getIntent().getStringExtra(LOCATION);
            goToFragment(ForecastFragment.class, false, getIntent().getExtras());
        }
    }

    private void getCurrentWeather(final String location) {

        new CurrentWeatherTask(new ResponseListener<WeatherInfo>() {
            @Override
            public void success(WeatherInfo weatherInfo) {
                System.out.println(weatherInfo);
                Bundle bundle = new Bundle();
                bundle.putParcelable(MiniForecastFragment.WEATHER_INFO, weatherInfo);
                bundle.putString(LOCATION, weatherInfo.getName());
                goToFragment(MiniForecastFragment.class, false, bundle);
            }

            @Override
            public void error() {

            }
        }).execute(location);
    }
}
