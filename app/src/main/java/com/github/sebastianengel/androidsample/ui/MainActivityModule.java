package com.github.sebastianengel.androidsample.ui;

import com.github.sebastianengel.androidsample.AppModule;

import dagger.Module;

/**
 * Module providing dependencies for the MainActivity and its fragments.
 */
@Module(
    injects = {
        MainActivity.class,
        UsernameInputFragment.class
    },
    addsTo = AppModule.class
)
public final class MainActivityModule {
}
