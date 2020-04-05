package io.mallinicouture.remote;

import io.mallinicouture.model.UserModel;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface UserService {

    @GET("/users/2")
    Single<UserModel> getUserModel();
}
