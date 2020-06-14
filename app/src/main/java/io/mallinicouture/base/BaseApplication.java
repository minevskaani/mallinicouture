package io.mallinicouture.base;

import android.app.Application;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.mallinicouture.di.component.AppComponent;
import io.mallinicouture.di.component.DaggerAppComponent;

public class BaseApplication extends DaggerApplication {
    private AppComponent appComponent;

    public BaseApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        //* 1
        appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);

        return appComponent;
         //*/
        // 2return DaggerAppComponent.builder().create(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
