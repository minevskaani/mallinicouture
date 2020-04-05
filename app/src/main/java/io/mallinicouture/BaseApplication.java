package io.mallinicouture;

import android.app.Application;

import io.mallinicouture.di.components.AppComponent;

public class BaseApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // appComponent = DaggerAppComponent.create();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
