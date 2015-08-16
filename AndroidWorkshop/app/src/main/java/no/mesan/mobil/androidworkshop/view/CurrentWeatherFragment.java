package no.mesan.mobil.androidworkshop.view;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import no.mesan.mobil.androidworkshop.R;
import no.mesan.mobil.androidworkshop.model.WeatherInfo;
import no.mesan.mobil.androidworkshop.task.CurrentWeatherTask;
import no.mesan.mobil.androidworkshop.task.ResponseListener;

public class CurrentWeatherFragment extends Fragment {

    public CurrentWeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String location = getArguments().getString("location");
        getCurrentWeather(location);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_weather, container, false);
    }

    private void getCurrentWeather(String location) {
        new CurrentWeatherTask(new ResponseListener<WeatherInfo>() {
            @Override
            public void success(WeatherInfo weatherInfo) {
                System.out.println(weatherInfo);
            }

            @Override
            public void error() {

            }
        }).execute(location);
    }
}
