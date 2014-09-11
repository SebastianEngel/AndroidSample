package com.github.sebastianengel.androidsample;

import android.app.Application;
import android.content.Context;

import dagger.ObjectGraph;

/**
 * TODO Comment
 */
public class App extends Application {

    private ObjectGraph objectGraph;

    ///////////////////////////////////////////////////////////////////////////
    // Application life cycle
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void onCreate() {
        super.onCreate();

        initApplicationObjectGraph();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Internal behavior
    ///////////////////////////////////////////////////////////////////////////

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }

    public void inject(Object object) {
        objectGraph.inject(object);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Internal behavior
    ///////////////////////////////////////////////////////////////////////////

    private void initApplicationObjectGraph() {
        objectGraph = ObjectGraph.create(Modules.list());
        objectGraph.inject(this);
    }

}
