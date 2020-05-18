package io.mallinicouture.backend.service;

import io.mallinicouture.backend.domain.*;
import io.mallinicouture.backend.exception.OrderException;
import io.mallinicouture.backend.repository.ClientRepository;
import io.mallinicouture.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private BasketService basketService; // When order is created, basket should be cleared

    public Order makeOrder(
            String username,
            String address,
            boolean payByCreditCard
    ) {
        Client client = clientRepository.findByUsername(username);
        Basket basket = client.getBasket();
        if (basket.getTotalPrice() == 0) {
            throw new OrderException("User basket is empty");
        }

        // Set up order
        Order order = new Order();

        order.setClient(basket.getClient());
        order.setAddress(address);
        order.setDelivered(false);
        order.setDresses(new ArrayList<>(basket.getDresses()));
        order.setTotalPrice(basket.getTotalPrice());

        if (payByCreditCard) {
            PaymentDetails pd = makePaymentByCrediCard(order);

            order.setPaymentDetails(pd);
        }

        // Reset basket
        basketService.clearBasket(basket);

        return orderRepository.save(order);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByUsername(String username) {
        Client client = clientRepository.findByUsername(username);

        return orderRepository.findAllByClient(client);
    }

    private PaymentDetails makePaymentByCrediCard(Order order) {

        Client client = order.getClient();
        CreditCard cc = client.getCreditCard();
        if (cc == null) {
            throw new OrderException("User " + client.getFirstName() + " doesn't have credit card");
        }

        // TODO: add a bank service?
        PaymentDetails pd = new PaymentDetails();
        pd.setClient(client);
        pd.setCreditCard(client.getCreditCard().getCardNumber());
        pd.setPaymentDate(new Date());
        pd.setPrice(order.getTotalPrice());
        order.setPayed(true);

        return pd;
    }
}
