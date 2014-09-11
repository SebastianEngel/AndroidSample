package com.github.sebastianengel.androidsample.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.github.sebastianengel.androidsample.R;
import com.github.sebastianengel.androidsample.data.GitHubClient;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Fragment used to input the GitHub username.
 */
public class UsernameInputFragment extends Fragment {

    @InjectView(R.id.username_field) EditText usernameField;
    @InjectView(R.id.request_user_info_button) Button requestUserInfoButton;
    @InjectView(R.id.progress_bar) ProgressBar progressBar;

    @Inject GitHubClient gitHubClient;

    ///////////////////////////////////////////////////////////////////////////
    // Fragment instantiation
    ///////////////////////////////////////////////////////////////////////////

    public static UsernameInputFragment newInstance() {
        return new UsernameInputFragment();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Fragment lifecycle
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_username_input, container, false);
        ButterKnife.inject(this, root);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Inject dependencies
        ((AbstractBaseActivity) getActivity()).getObjectGraph().inject(this);
    }

    @Override
    public void onStop() {
        super.onStop();

        requestUserInfoButton.setEnabled(true);
        progressBar.setVisibility(View.GONE);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Click listener
    ///////////////////////////////////////////////////////////////////////////

    @OnClick(R.id.request_user_info_button)
    public void onClickRequestUserInfo() {
        String username = usernameField.getText().toString().trim();

        requestUserInfoButton.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);

        gitHubClient.fetchUser(username);
    }

}
