package io.mallinicouture.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;
import io.mallinicouture.presentation.base.BaseApplication;
import io.mallinicouture.di.module.ActivityBindingModule;
import io.mallinicouture.di.module.ContextModule;
import io.mallinicouture.di.module.NetworkModule;

@Singleton
@Component(modules = {NetworkModule.class, ContextModule.class, AndroidSupportInjectionModule.class, ActivityBindingModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    //* 1
    void inject(BaseApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    // */
    /* 2
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseApplication> {}
     */
}
