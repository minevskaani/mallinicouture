package io.mallinicouture.data.repository;

import java.util.List;

import javax.inject.Inject;

import io.mallinicouture.data.model.Order;
import io.mallinicouture.data.remote.OrderService;
import io.reactivex.rxjava3.core.Single;

public class OrderRepository {

    private final OrderService orderService;

    @Inject
    public OrderRepository(OrderService orderService) {
        this.orderService = orderService;
    }

    public Single<List<Order>> getUserOrders() {
        return orderService.getUserOrders();
    }

    public Single<Order> makeOrder(String address, boolean payByCreditCard) {
        return orderService.makeOrder(address, payByCreditCard);
    }
}
