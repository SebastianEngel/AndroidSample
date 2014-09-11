package com.github.sebastianengel.androidsample.data;

import com.github.sebastianengel.androidsample.BuildConfig;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

/**
 * Module for API related dependencies.
 *
 * @author Sebastian Engel
 */
@Module(
    complete = false,
    library = true
)
public final class ApiModule {

    @Provides @Singleton
    RestAdapter provideRestAdapter() {
        return new RestAdapter.Builder()
            .setEndpoint("https://api.github.com")
            .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
            .build();
    }

    @Provides @Singleton
    IGitHubService provideGitHubServiceEndpoint(RestAdapter restAdapter) {
        return restAdapter.create(IGitHubService.class);
    }

    @Provides @Singleton
    GitHubClient provideGitHubClient(IGitHubService gitHubService, Bus eventBus) {
        return new GitHubClient(gitHubService, eventBus);
    }

}
