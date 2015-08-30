package no.mesan.mobil.androidworkshop.view.forecast;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import no.mesan.mobil.androidworkshop.R;
import no.mesan.mobil.androidworkshop.model.Forecast;
import no.mesan.mobil.androidworkshop.model.ForecastType;
import no.mesan.mobil.androidworkshop.model.WeatherInfo;
import no.mesan.mobil.androidworkshop.task.ForecastTask;
import no.mesan.mobil.androidworkshop.task.ResponseListener;
import no.mesan.mobil.androidworkshop.view.main.SearchFragment;

public class ForecastFragment extends Fragment {

    private RecyclerView recyclerView;
    private ForecastAdapter adapter;
    private ForecastType forecastType;

    private String location;

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_forecast, container, false);

        location = getArguments().getString(ForecastActivity.LOCATION, "Oslo");
        forecastType = ForecastType.valueOf(getArguments().getString(SearchFragment.FORECAST_TYPE, ForecastType.FIVE_DAY.name()));

        initGui();
        initData();

        return view;
    }

    private void initGui() {
        // Oppgave 4a
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewForecast);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter = new ForecastAdapter(view.getContext(), new ForecastClickListener() {
            @Override
            public void onClick(WeatherInfo weatherInfo) {
                Bundle bundle = new Bundle();
                bundle.putString(ForecastActivity.LOCATION, location);
                bundle.putParcelable(MiniForecastFragment.WEATHER_INFO, weatherInfo);
                ((ForecastActivity) getActivity()).goToFragment(MiniForecastFragment.class, true, bundle);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void initData() {

        // Oppgave 4d - Bruk ForecastTask til å hente værdata
        new ForecastTask(new ResponseListener<Forecast>() {
            @Override
            public void success(Forecast weatherInfoList) {
                adapter.setWeather(weatherInfoList.getList());
                location = weatherInfoList.getCity().getName();
            }

            @Override
            public void error() {

            }
        }).execute(location);
    }
}
