package com.github.sebastianengel.androidsample.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.sebastianengel.androidsample.R;
import com.github.sebastianengel.androidsample.data.model.User;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Fragment showing the user's info and its avatar image.
 */
public class UserInfoFragment extends Fragment {

    @InjectView(R.id.avatar_image) ImageView avatarImageView;
    @InjectView(R.id.login) TextView login;
    @InjectView(R.id.id) TextView id;
    @InjectView(R.id.name) TextView name;
    @InjectView(R.id.location) TextView location;
    @InjectView(R.id.public_repos) TextView publicRepos;

    private User user;

    ///////////////////////////////////////////////////////////////////////////
    // Fragment instantiation
    ///////////////////////////////////////////////////////////////////////////

    public static UserInfoFragment newInstance(User user) {
        UserInfoFragment fragment = new UserInfoFragment();
        fragment.user = user;
        return fragment;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Fragment lifecycle
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user_info, container, false);
        ButterKnife.inject(this, root);

        // Set the static values
        login.setText(user.login);
        id.setText(String.valueOf(user.id));
        name.setText(user.name);
        location.setText(user.location);
        publicRepos.setText(String.valueOf(user.publicRepos));

        // Load the avatar image
        Picasso.with(getActivity())
            .load(user.avatarUrl)
            .placeholder(R.drawable.github)
            .into(avatarImageView);

        return root;
    }

}
