package io.mallinicouture.presentation.base;

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
        appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);

        return appComponent;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
