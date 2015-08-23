package no.mesan.mobil.androidworkshop.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import no.mesan.mobil.androidworkshop.R;

public class BaseActivity extends AppCompatActivity {

    public void goToFragment(Class<? extends Fragment> fragment, boolean addToBackstack, Bundle bundle) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        try {
            Fragment fragmentInstance = fragment.newInstance();
            fragmentInstance.setArguments(bundle);
            transaction.replace(R.id.container, fragmentInstance, fragment.getName());
            if (addToBackstack) {
                transaction.addToBackStack(null);
            }
            transaction.commitAllowingStateLoss();
        } catch (Exception e) {
            Log.e(getClass().getName(), "Failed to create fragment instance: ", e);
        }
    }

    public void goToFragment(Class<? extends Fragment> fragment, boolean addToBackstack) {
        goToFragment(fragment, addToBackstack, null);
    }
}
