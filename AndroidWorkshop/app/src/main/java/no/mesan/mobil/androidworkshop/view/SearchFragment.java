package no.mesan.mobil.androidworkshop.view;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;
import java.util.Set;

import no.mesan.mobil.androidworkshop.R;

public class SearchFragment extends Fragment {

    private EditText editTextLocation;
    private Button buttonSearch;

    private Set<String> locations;

    private static final String PREFERENCES_NAME = "Preferences";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        initLocationHistory();
        initViews(view);
        initListeners();

        return view;
    }

    private void initLocationHistory() {
        SharedPreferences prefs = getActivity().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        locations = prefs.getStringSet("locations", null);
    }

    private void initViews(View view) {
        editTextLocation = (EditText) view.findViewById(R.id.editTextLocation);
        buttonSearch = (Button) view.findViewById(R.id.buttonSearch);
    }

    private void initListeners() {
         buttonSearch.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 locations.add(editTextLocation.getText().toString());
             }
         });
    }
}
