package no.mesan.mobil.androidworkshop.view.main;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.HashSet;
import java.util.LinkedHashSet;

import no.mesan.mobil.androidworkshop.R;
import no.mesan.mobil.androidworkshop.model.ForecastType;
import no.mesan.mobil.androidworkshop.view.DividerItemDecoration;

public class SearchFragment extends Fragment {
    private static final String PREFERENCES_NAME = "Preferences";
    public static final String LOCATION_KEY = "locationKey";
    public static final String FORECAST_TYPE = "forecastType";

    private RadioGroup radioGroupSearchMode;

    private EditText editTextLocation;
    private Button buttonSearch;
    private RecyclerView recyclerViewLocations;

    private SharedPreferences sharedPreferences;
    private LocationAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        initViews(view);

        /* Oppgave 2b
         * Kommenter denne inn igjen når recyclerviewet er bundet opp */
        initAdapters();
        initListeners();

        return view;
    }

    private LinkedHashSet<String> getLocationHistory() {
        sharedPreferences = getActivity().getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
        return new LinkedHashSet<>(sharedPreferences.getStringSet("locations", new HashSet<String>()));
    }

    private void initViews(View view) {
        editTextLocation = (EditText) view.findViewById(R.id.editTextLocation);
        buttonSearch = (Button) view.findViewById(R.id.buttonSearch);

        // Oppgave 2b Koble opp recyclerview

        // Oppgave 6
        radioGroupSearchMode = (RadioGroup) view.findViewById(R.id.radioGroupSearchMode);
    }

    // Oppgave 3 og 6
    private void initAdapters() {
        LinkedHashSet<String> locations = getLocationHistory();
        adapter = new LocationAdapter(locations, new LocationItemClickListener() {
            @Override
            public void onClick(String location) {
                showWeatherForLocation(location);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerViewLocations.setLayoutManager(linearLayoutManager);
        recyclerViewLocations.setAdapter(adapter);

        Drawable drawable = getResources().getDrawable(R.drawable.abc_list_divider_mtrl_alpha);

        drawable.setColorFilter(getResources().getColor(R.color.red_dark), PorterDuff.Mode.SRC_IN);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(drawable);
        recyclerViewLocations.addItemDecoration(dividerItemDecoration);
    }

    private void initListeners() {

        // Oppgave 3 - Lage lytter for søkeknapp
        // Oppgave 6 - Endre lytter for søkeknapp
         buttonSearch.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String location = editTextLocation.getText().toString();

                 // Make sure location string is not empty
                 if (!location.isEmpty()) {
                     adapter.addLocation(location);
                     saveLocations();
                     hideKeyboard();

                     showWeatherForLocation(location);
                 }
             }
         });
    }

    // Oppgave 3 + 6
    private void showWeatherForLocation(String location) {
        ForecastType forecastType;

        switch (radioGroupSearchMode.getCheckedRadioButtonId()) {
            case R.id.radioButtonCurrentWeather:
                forecastType = ForecastType.NOW;
                break;
            default:
                forecastType = ForecastType.FIVE_DAY;
                break;
        }

        ((MainActivity) getActivity()).goToForecastActivity(location, forecastType);
    }

    private void saveLocations() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("locations", adapter.getLocations());
        editor.apply();
    }

    private void hideKeyboard() {
        // Check if no view has focus:
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
