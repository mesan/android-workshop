package no.mesan.mobil.androidworkshop.view.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import no.mesan.mobil.androidworkshop.R;
import no.mesan.mobil.androidworkshop.view.BaseActivity;
import no.mesan.mobil.androidworkshop.view.NavigationDrawerFragment;

public class MainActivity extends BaseActivity {

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

        // Set our toolbar as ActionBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(title);

        // Set the status bar color.
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

    /* Hjelpemetode for å åpne et nytt fragment, med bundle med argumenter */
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

    /* Hjelpemetode for å åpne et nytt fragment, uten bundle med argumenter*/
    public void goToFragment(Class<? extends Fragment> fragment) {
        goToFragment(fragment, null);
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
}
