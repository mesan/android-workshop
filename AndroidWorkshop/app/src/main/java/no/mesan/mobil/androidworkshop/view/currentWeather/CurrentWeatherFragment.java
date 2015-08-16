package no.mesan.mobil.androidworkshop.view.currentWeather;

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
import no.mesan.mobil.androidworkshop.task.CurrentWeatherTask;
import no.mesan.mobil.androidworkshop.task.ResponseListener;
import no.mesan.mobil.androidworkshop.util.DateFormatter;
import no.mesan.mobil.androidworkshop.util.TemperatureFormatter;
import no.mesan.mobil.androidworkshop.view.SearchFragment;

public class CurrentWeatherFragment extends Fragment {

    private ImageView imageViewWeatherIcon;
    private TextView textViewLocation;
    private TextView textViewTemperature;
    private TextView textViewDate;

    public CurrentWeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current_weather, container, false);
        initViews(view);

        String location = getArguments().getString(SearchFragment.LOCATION_KEY);
        getCurrentWeather(location);


        return view;
    }

    private void initViews(View view) {
        textViewLocation = (TextView) view.findViewById(R.id.textViewLocation);
        textViewDate = (TextView) view.findViewById(R.id.textViewDate);
        imageViewWeatherIcon = (ImageView) view.findViewById(R.id.imageViewWeatherIcon);
        textViewTemperature = (TextView) view.findViewById(R.id.textViewTemperature);
    }

    private void getCurrentWeather(String location) {

        new CurrentWeatherTask(new ResponseListener<WeatherInfo>() {
            @Override
            public void success(WeatherInfo weatherInfo) {
                System.out.println(weatherInfo);
                updateViews(weatherInfo);
            }

            @Override
            public void error() {

            }
        }).execute(location);
    }

    private void updateViews(WeatherInfo weatherInfo) {
        Picasso.with(getActivity()).load("http://openweathermap.org/img/w/" + weatherInfo.getWeather().get(0).getIcon() + ".png").into(imageViewWeatherIcon);
        textViewLocation.setText(weatherInfo.getName());
        textViewTemperature.setText(TemperatureFormatter.format(weatherInfo.getMain().getTemp()));
        textViewDate.setText(DateFormatter.formatDate(weatherInfo.getDt()));
    }
}
