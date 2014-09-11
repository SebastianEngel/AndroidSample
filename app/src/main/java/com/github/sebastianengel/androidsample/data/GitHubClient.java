package com.github.sebastianengel.androidsample.data;

import com.github.sebastianengel.androidsample.data.events.UserInfoReceivedEvent;
import com.github.sebastianengel.androidsample.data.model.User;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Application scoped singleton used to asynchronously access the GitHub API.
 */
@Singleton
public class GitHubClient {

    private IGitHubService gitHubService;
    private Bus eventBus;

    public GitHubClient(IGitHubService gitHubService, Bus eventBus) {
        this.gitHubService = gitHubService;
        this.eventBus = eventBus;
    }

    public void fetchUser(String username) {
        gitHubService.getUser(username, new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                eventBus.post(new UserInfoReceivedEvent(user));
            }

            @Override
            public void failure(RetrofitError error) {
                eventBus.post(false);
            }
        });
    }

}
