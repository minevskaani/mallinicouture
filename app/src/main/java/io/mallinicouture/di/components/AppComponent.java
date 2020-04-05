package io.mallinicouture.di.components;

import javax.inject.Singleton;

import dagger.Component;
import io.mallinicouture.MainActivity;
import io.mallinicouture.di.modules.ContextModule;
import io.mallinicouture.di.modules.NetworkModule;

@Singleton
@Component(modules = {NetworkModule.class, ContextModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);
}
