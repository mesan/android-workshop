package no.mesan.mobil.androidworkshop.view.forecast;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import no.mesan.mobil.androidworkshop.R;
import no.mesan.mobil.androidworkshop.model.WeatherInfo;
import no.mesan.mobil.androidworkshop.util.DateFormatter;
import no.mesan.mobil.androidworkshop.util.TemperatureFormatter;

public class MiniForecastFragment extends Fragment {

    public static final String WEATHER_INFO = "weatherInfo";

    private ImageView imageViewWeatherIcon;
    private TextView textViewLocation;
    private TextView textViewTemperature;
    private TextView textViewDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current_weather, container, false);
        initViews(view);

        Bundle bundle = getArguments();
        if (bundle != null) {
            WeatherInfo weatherInfo = bundle.getParcelable(WEATHER_INFO);
            updateViews(weatherInfo);
        }

        return view;
    }

    private void initViews(View view) {
        textViewLocation = (TextView) view.findViewById(R.id.textViewLocation);
        textViewDate = (TextView) view.findViewById(R.id.textViewDate);
        imageViewWeatherIcon = (ImageView) view.findViewById(R.id.imageViewWeatherIcon);
        textViewTemperature = (TextView) view.findViewById(R.id.textViewTemperature);
    }

    private void updateViews(WeatherInfo weatherInfo) {
        if (weatherInfo != null && weatherInfo.getWeather() != null) {
            Picasso.with(getActivity()).load("http://openweathermap.org/img/w/" + weatherInfo.getWeather().get(0).getIcon() + ".png").into(imageViewWeatherIcon);
            textViewLocation.setText(weatherInfo.getName());

            double temp;
            if (weatherInfo.getMain() != null) {
                temp = weatherInfo.getMain().getTemp();
            }
            else {
                temp = weatherInfo.getTemp().getDay();
            }

            textViewTemperature.setText(TemperatureFormatter.format(temp));
            textViewDate.setText(DateFormatter.formatDate(weatherInfo.getDt()));
        }
    }
}
