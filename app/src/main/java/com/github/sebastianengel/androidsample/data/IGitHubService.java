package com.github.sebastianengel.androidsample.data;

import com.github.sebastianengel.androidsample.data.model.User;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Interface defining the GitHub endpoint.
 */
public interface IGitHubService {

    @GET("/users/{username}")
    public void getUser(@Path("username") String username, Callback<User> callback);

}
