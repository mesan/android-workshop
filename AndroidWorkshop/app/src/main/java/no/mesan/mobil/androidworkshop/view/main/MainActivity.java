package no.mesan.mobil.androidworkshop.view.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import no.mesan.mobil.androidworkshop.R;
import no.mesan.mobil.androidworkshop.model.ForecastType;
import no.mesan.mobil.androidworkshop.view.BaseActivity;
import no.mesan.mobil.androidworkshop.view.NavigationDrawerFragment;
import no.mesan.mobil.androidworkshop.view.about.AboutAppFragment;
import no.mesan.mobil.androidworkshop.view.forecast.ForecastActivity;

public class MainActivity extends BaseActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment navigationDrawerFragment;

    /**
     * Used to store the last screen title.
     */
    private CharSequence title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Your normal setup. Blah blah ...

        // As we're using a Toolbar, we should retrieve it and set it
        // to be our ActionBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(title);

        // Now retrieve the DrawerLayout so that we can set the status bar color.
        // This only takes effect on Lollipop, or when using translucentStatusBar
        // on KitKat.
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setStatusBarBackgroundColor(Color.BLUE);

        navigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        title = getTitle();

        // Set up the drawer.
        navigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        switch (position) {
            case 0:
                goToFragment(SearchFragment.class);
                break;
            case 1:
                goToFragment(AboutAppFragment.class);
                break;
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                title = getString(R.string.title_section1);
                break;
            case 2:
                title = getString(R.string.title_section2);
                break;
        }
    }

    public void goToFragment(Class<? extends Fragment> fragment, Bundle bundle) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        try {
            Fragment fragmentInstance = fragment.newInstance();
            fragmentInstance.setArguments(bundle);
            transaction.replace(R.id.container, fragmentInstance, fragment.getName());
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        } catch (Exception e) {
            Log.e(getClass().getName(), "Failed to create fragment instance: ", e);
        }
    }

    public void goToFragment(Class<? extends Fragment> fragment) {
        goToFragment(fragment, null);
    }

    public void goToForecastActivity(String location, ForecastType forecastType) {
        Intent intent = new Intent(this, ForecastActivity.class);
        intent.putExtra(ForecastActivity.LOCATION, location);
        intent.putExtra(ForecastActivity.FORECAST_TYPE, forecastType.name());
        startActivity(intent);
    }
}
