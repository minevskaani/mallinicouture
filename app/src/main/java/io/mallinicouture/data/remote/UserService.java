package io.mallinicouture.data.remote;

import io.mallinicouture.data.model.LoggedInUser;
import io.mallinicouture.ui.creditcard.model.CreditCard;
import io.mallinicouture.data.model.User;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.POST;

public interface UserService {

    // TODO @POST("/api/users/login")
    // TODO Single<LoginResponse> login(LoginRequest loginRequest);

    @POST("/api/users/register}")
    Single<LoggedInUser> register(User user);

    @POST("/api/users/credit-card")
    Single<CreditCard> updateCreditCard(CreditCard creditCard);

}
