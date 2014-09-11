package com.github.sebastianengel.androidsample.ui;

import android.os.Bundle;

import com.github.sebastianengel.androidsample.R;
import com.github.sebastianengel.androidsample.data.events.UserInfoReceivedEvent;
import com.squareup.otto.Subscribe;

/**
 * TODO Comment
 */
public class MainActivity extends AbstractBaseActivity {

    ///////////////////////////////////////////////////////////////////////////
    // Activity lifecycle
    ///////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            // Initially show the UsernameInputFragment
            replaceFragment(UsernameInputFragment.newInstance(), false);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Event bus subscriptions
    ///////////////////////////////////////////////////////////////////////////

    @Subscribe
    public void onUserInfoReceived(UserInfoReceivedEvent event) {
        replaceFragment(UserInfoFragment.newInstance(event.user), true);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Dependency injection
    ///////////////////////////////////////////////////////////////////////////

    @Override
    protected Object[] getUiModules() {
        return new Object[] {
            new MainActivityModule()
        };
    }

}
