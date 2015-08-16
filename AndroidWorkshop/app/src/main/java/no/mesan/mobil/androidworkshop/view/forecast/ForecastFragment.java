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

/**
 * Created by Thomas on 16.08.2015.
 */
public class ForecastFragment extends Fragment {

    private RecyclerView recyclerView;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initGui();
        view = inflater.inflate(R.layout.fragment_forecast, container, false);
        return view;
    }

    private void initGui() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewForecast);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
