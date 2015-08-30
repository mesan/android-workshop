package no.mesan.mobil.androidworkshop.view.forecast;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import no.mesan.mobil.androidworkshop.R;
import no.mesan.mobil.androidworkshop.model.Forecast;
import no.mesan.mobil.androidworkshop.model.ForecastType;
import no.mesan.mobil.androidworkshop.model.WeatherInfo;
import no.mesan.mobil.androidworkshop.task.ForecastTask;
import no.mesan.mobil.androidworkshop.task.ResponseListener;
import no.mesan.mobil.androidworkshop.view.main.SearchFragment;

public class ForecastFragment extends Fragment {

    private TextView textViewLocation;

    private String location;

    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_forecast, container, false);

        location = getArguments().getString(ForecastActivity.LOCATION, "Oslo");

        initViews();
        initData();

        return view;
    }

    private void initViews() {
        // Oppgave 3d - Initialiser textview og sett teksten
        textViewLocation = (TextView) view.findViewById(R.id.textViewLocation);
        textViewLocation.setText(location);

        // Oppgave 4b - Fjern textviewet over. Lag recyclerview med adapter av typen ForecastAdapter

    }

    private void initData() {
        // Oppgave 4d - Bruk ForecastTask til å hente værdata

    }
}
