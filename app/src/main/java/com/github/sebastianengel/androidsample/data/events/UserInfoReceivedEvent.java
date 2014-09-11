package com.github.sebastianengel.androidsample.data.events;

import com.github.sebastianengel.androidsample.data.model.User;

/**
 * Event used to inform a subscriber that the user info has been received from the API.
 */
public class UserInfoReceivedEvent {

    public final boolean success;
    public final User user;

    public UserInfoReceivedEvent(User user) {
        this.success = true;
        this.user = user;
    }

    public UserInfoReceivedEvent(boolean success) {
        this.success = success;
        this.user = null;
    }

}
