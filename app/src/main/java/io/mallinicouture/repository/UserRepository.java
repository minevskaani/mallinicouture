package io.mallinicouture.repository;

import javax.inject.Inject;

import io.mallinicouture.model.UserModel;
import io.mallinicouture.remote.UserService;
import io.reactivex.rxjava3.core.Single;

public class UserRepository {

    private UserService userService;

    @Inject
    public UserRepository(UserService userService) {
        this.userService = userService;
    }

    public Single<UserModel> modelSingle() {
        return userService.getUserModel();
    }
}
