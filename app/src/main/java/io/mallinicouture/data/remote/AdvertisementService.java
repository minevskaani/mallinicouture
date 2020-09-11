package io.mallinicouture.data.remote;

import java.util.List;

import io.mallinicouture.data.model.Advertisement;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface AdvertisementService {

    @GET("/api/advertisement")
    Single<List<Advertisement>> getAllAdvertisements();
}
