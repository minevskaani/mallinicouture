package io.mallinicouture.backend.web;

import io.mallinicouture.backend.domain.Order;
import io.mallinicouture.backend.service.OrderService;
import io.mallinicouture.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    // TODO: remove in production
    @GetMapping("/test")
    public ResponseEntity<?> getOrders() {
        List<Order> orders = orderService.getOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("")
    public  ResponseEntity<?> getUserOrders(Principal principal) {
        List<Order> orders = orderService.getOrdersByUsername(principal.getName());

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> makeOrder(
            Principal principal,
            @RequestParam(name = "address") String address,
            @RequestParam(name = "payByCreditCard") boolean payByCrediCard
    ) {
        Order order = orderService.makeOrder(principal.getName(), address, payByCrediCard);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }


}
