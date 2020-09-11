package io.mallinicouture.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.mallinicouture.data.remote.AdvertisementService;
import io.mallinicouture.data.remote.CategoryService;
import io.mallinicouture.data.remote.UserService;
import io.mallinicouture.network.TokenAuthenticator;
import io.mallinicouture.network.TokenInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public abstract class NetworkModule {

    private static final String BASE_URL = "http://192.168.1.4:8080";

    @Provides
    @Singleton
    static Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    static OkHttpClient provideOkHttpClient(TokenInterceptor tokenInterceptor,
                                            TokenAuthenticator tokenAuthenticator) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient()
                .newBuilder()
                .authenticator(tokenAuthenticator)
                .addInterceptor(tokenInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Provides
    @Singleton
    static Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }


    @Provides
    @Singleton
    static UserService provideUserService(Retrofit retrofit) {
        return retrofit.create(UserService.class);
    }

    @Provides
    @Singleton
    static CategoryService provideCategoryService(Retrofit retrofit) {
        return retrofit.create(CategoryService.class);
    }

    @Provides
    @Singleton
    static AdvertisementService provideAdvertisementService(Retrofit retrofit) {
        return retrofit.create(AdvertisementService.class);
    }

}
