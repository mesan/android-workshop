package no.mesan.mobil.androidworkshop.view.forecast;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import no.mesan.mobil.androidworkshop.R;
import no.mesan.mobil.androidworkshop.model.WeatherInfo;
import no.mesan.mobil.androidworkshop.model.Wind;
import no.mesan.mobil.androidworkshop.util.DateFormatter;
import no.mesan.mobil.androidworkshop.util.TemperatureFormatter;

// Oppgave 5
public class MiniForecastFragment extends Fragment {

    public static final String WEATHER_INFO = "weatherInfo";

    private ImageView imageViewWeatherIcon;
    private TextView textViewLocation;
    private TextView textViewTime;
    private TextView textViewTemperature;
    private TextView textViewDate;
    private ImageView imageViewWindDirection;
    private TextView textViewWindSpeed;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mini_forecast, container, false);
        initViews(view);

        // Oppgave 5d: Ta imot data og vis i ditt GUI
        Bundle bundle = getArguments();
        if (bundle != null) {
            WeatherInfo weatherInfo = bundle.getParcelable(WEATHER_INFO);
            String location = bundle.getString(ForecastActivity.LOCATION);
            updateViews(weatherInfo, location);
        }

        return view;
    }

    private void initViews(View view) {
        textViewLocation = (TextView) view.findViewById(R.id.textViewLocation);
        textViewTime = (TextView) view.findViewById(R.id.textViewTime);
        textViewDate = (TextView) view.findViewById(R.id.textViewDate);
        imageViewWeatherIcon = (ImageView) view.findViewById(R.id.imageViewWeatherIcon);
        textViewTemperature = (TextView) view.findViewById(R.id.textViewTemperature);
        imageViewWindDirection = (ImageView) view.findViewById(R.id.imageViewWindDirection);
        textViewWindSpeed = (TextView) view.findViewById(R.id.textViewWindSpeed);
    }

    // Oppgave 5
    private void updateViews(WeatherInfo weatherInfo, String location) {
        if (weatherInfo != null && weatherInfo.getWeather() != null) {
            Picasso.with(getActivity()).load("http://openweathermap.org/img/w/" + weatherInfo.getWeather().get(0).getIcon() + ".png").into(imageViewWeatherIcon);

            textViewLocation.setText(location);

            double temp;
            if (weatherInfo.getMain() != null) {
                temp = weatherInfo.getMain().getTemp();
            }
            else {
                temp = weatherInfo.getTemp().getDay();
            }

            double speed;
            double deg;
            Wind wind = weatherInfo.getWind();
            if (wind != null) {
                speed = wind.getSpeed();
                deg = wind.getDeg();
            }
            else {
                speed = weatherInfo.getSpeed();
                deg = weatherInfo.getDeg();
            }

            textViewTemperature.setText(TemperatureFormatter.format(temp));
            textViewDate.setText(DateFormatter.formatDate(weatherInfo.getDt()));
            textViewTime.setText("Kl. " + DateFormatter.formatTime(weatherInfo.getDt()));
            textViewWindSpeed.setText(speed + " m/s");

            Log.d("Degree", "" + deg);
            imageViewWindDirection.animate().rotation((int) deg).setDuration(1000).setInterpolator(new OvershootInterpolator());
        }
    }
}
