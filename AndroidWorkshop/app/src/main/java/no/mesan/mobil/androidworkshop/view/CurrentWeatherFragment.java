package no.mesan.mobil.androidworkshop.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import no.mesan.mobil.androidworkshop.R;
import no.mesan.mobil.androidworkshop.model.CurrentWeather;
import no.mesan.mobil.androidworkshop.task.CurrentWeatherTask;
import no.mesan.mobil.androidworkshop.task.ResponseListener;

public class CurrentWeatherFragment extends Fragment {

    public CurrentWeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getCurrentWeather();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_weather, container, false);
    }

    private void getCurrentWeather() {
        new CurrentWeatherTask(new ResponseListener<CurrentWeather>() {
            @Override
            public void success(CurrentWeather currentWeather) {
                System.out.println(currentWeather);
            }

            @Override
            public void error() {

            }
        }).execute();
    }
}
