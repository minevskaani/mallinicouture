package io.mallinicouture.data.remote;

import java.util.List;

import io.mallinicouture.data.model.Order;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OrderService {

    @GET("/api/order")
    Single<List<Order>> getUserOrders();

    @POST("/api/order")
    Single<Order> makeOrder(String address, boolean payByCreditCard);
}
