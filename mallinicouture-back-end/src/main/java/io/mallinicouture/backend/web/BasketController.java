package io.mallinicouture.backend.web;

import io.mallinicouture.backend.domain.Basket;
import io.mallinicouture.backend.domain.DressSize;
import io.mallinicouture.backend.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/basket")
public class BasketController {

    @Autowired
    private BasketService basketService;

    @GetMapping("")
    public ResponseEntity<?> getBasket(Principal principal) {
        Basket basket = basketService.getBaskedByUsername(principal.getName());

        return new ResponseEntity<>(basket, HttpStatus.OK);
    }

    @PostMapping("/dress")
    public ResponseEntity<?> addDressToBasket(
            @RequestParam(value = "id") long id,
            @RequestParam(value = "quantity") int quantity,
            @Valid @RequestParam(value = "size") DressSize dressSize,
            Principal principal) {


        Basket basket = basketService.getBaskedByUsername(principal.getName());

        basket = basketService.addDress(basket, id, quantity, dressSize);

        return new ResponseEntity<>(basket, HttpStatus.OK);
    }

    @DeleteMapping("/dress")
    public ResponseEntity<?> removeDressFromBasket(
            @RequestParam("id") long basketDressId,
            Principal principal) {
        Basket basket = basketService.getBaskedByUsername(principal.getName());

        basket = basketService.removeDress(basket, basketDressId);

        return new ResponseEntity<>(basket, HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<?> clearBasket(Principal principal) {
        Basket basket = basketService.getBaskedByUsername(principal.getName());

        basket = basketService.clearBasketAndDresses(basket);

        return new ResponseEntity<>(basket, HttpStatus.OK);
    }
}
