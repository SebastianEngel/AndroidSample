package com.github.sebastianengel.androidsample.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * TODO Comment
 */
public class User {

    public String login;
    public int id;
    @SerializedName("avatar_url")
    public String avatarUrl;
    public String name;
    public String location;
    @SerializedName("public_repos")
    public int publicRepos;

}
