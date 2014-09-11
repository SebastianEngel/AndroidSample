package com.github.sebastianengel.androidsample.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.github.sebastianengel.androidsample.App;
import com.github.sebastianengel.androidsample.R;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import dagger.ObjectGraph;

/**
 * Abstract base activity providing the dependency injection for sub-activities,
 * registering to the event bus, etc.
 */
public abstract class AbstractBaseActivity extends Activity {

    @Inject Bus eventBus;

    private ObjectGraph activityScopedObjectGraph;

    ///////////////////////////////////////////////////////////////////////////
    // Activity lifecycle
    ///////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        injectDependencies();
    }

    @Override
    protected void onResume() {
        super.onResume();
        eventBus.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        eventBus.unregister(this);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Public behavior
    ///////////////////////////////////////////////////////////////////////////

    public ObjectGraph getObjectGraph() {
        return activityScopedObjectGraph;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Additional behavior for sub-activities
    ///////////////////////////////////////////////////////////////////////////

    protected abstract Object[] getUiModules();

    protected void replaceFragment(Fragment fragmentToShow, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.replace(R.id.container, fragmentToShow);
        fragmentTransaction.commit();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Private behavior
    ///////////////////////////////////////////////////////////////////////////

    private void injectDependencies() {
        activityScopedObjectGraph = App.get(this).getObjectGraph().plus(getUiModules());
        activityScopedObjectGraph.inject(this);
    }


}
