package io.mallinicouture.data.remote;

import java.util.List;

import io.mallinicouture.data.model.Dress;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface DressService {

    @GET("/api/dresses")
    Single<List<Dress>> getAllDresses();

    @GET("/api/dresses")
    Single<List<Dress>> getAllDressesByCatId(Long categoryId);

    @GET("/api/dresses/{dress_id}")
    Single<Dress> getDressById(Long dressId);

}
