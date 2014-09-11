package com.github.sebastianengel.androidsample;

import com.github.sebastianengel.androidsample.data.ApiModule;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * TODO Comment
 */
@Module(
    injects = {
        App.class
    },
    includes = {
        ApiModule.class
    },
    complete = true
)
public final class AppModule {

    @Provides @Singleton
    Bus provideEventBus() {
        return new Bus();
    }

}
