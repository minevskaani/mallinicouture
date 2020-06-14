package io.mallinicouture.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.mallinicouture.remote.UserService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public abstract class NetworkModule {

    private static final String BASE_URL = "https://api.malliniculture.io";

    @Singleton
    @Provides
    static Retrofit provideRetrofit() {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    @Provides
    @Singleton
    static UserService provideUserService(Retrofit retrofit) {
        return retrofit.create(UserService.class);
    }
}
