package io.mallinicouture.data.repository;

import javax.inject.Inject;

import io.mallinicouture.data.model.LoggedInUser;
import io.mallinicouture.data.model.User;
import io.mallinicouture.data.remote.UserService;
import io.mallinicouture.ui.creditcard.model.CreditCard;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.POST;

public class UserRepository {

    private UserService userService;

    @Inject
    public UserRepository(UserService userService) {
        this.userService = userService;
    }

    public Single<LoggedInUser> register(User user) {
        return userService.register(user);
    }

    public Single<CreditCard> updateCreditCard(CreditCard creditCard) {
        return userService.updateCreditCard(creditCard);
    }
}
