package io.mallinicouture.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.mallinicouture.remote.UserService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public abstract class NetworkModule {

    @Provides
    @Singleton
    static Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typecode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    static UserService provideUserService(Retrofit retrofit) {
        return retrofit.create(UserService.class);
    }
}
