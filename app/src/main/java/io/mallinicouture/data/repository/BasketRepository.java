package io.mallinicouture.data.repository;

import javax.inject.Inject;

import io.mallinicouture.data.remote.BasketService;

public class BasketRepository {

    private final BasketService basketService;

    @Inject
    public BasketRepository(BasketService basketService) {
        this.basketService = basketService;
    }

    // TODO: public Single<>
}
