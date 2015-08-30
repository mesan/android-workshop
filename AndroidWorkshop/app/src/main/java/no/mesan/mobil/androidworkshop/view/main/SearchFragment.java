package no.mesan.mobil.androidworkshop.view.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import no.mesan.mobil.androidworkshop.R;

public class SearchFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        initViews(view);
        initListeners();

        return view;
    }

    private void initViews(View view) {
        // Oppgave 1a Koble opp knapp og søkefelt

    }

    private void initListeners() {
        // Oppgave 1b - Lage lytter for søkeknapp

    }
}
