package io.mallinicouture.backend.service;

import io.mallinicouture.backend.domain.Basket;
import io.mallinicouture.backend.domain.Dress;
import io.mallinicouture.backend.domain.DressSize;
import io.mallinicouture.backend.domain.OrderedDress;
import io.mallinicouture.backend.exception.DressNotFoundException;
import io.mallinicouture.backend.repository.BasketRepository;
import io.mallinicouture.backend.repository.ClientRepository;
import io.mallinicouture.backend.repository.OrderedDressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private OrderedDressRepository orderedDressRepository;
    @Autowired
    private DressService dressService;

    @Autowired
    private ClientRepository clientRepository;

    public Basket getBaskedByUsername(String username) {
        return clientRepository.findByUsername(username).getBasket();
    }

    public Basket addDress(Basket basket, long dressId, int quantity, DressSize dressSize) {
        Dress dress = dressService.getById(dressId);

        OrderedDress newODress = new OrderedDress(null, dress, dressSize, quantity);

        List<OrderedDress> dresses = basket.getDresses();
        // If dress is not in list
        boolean found = false;
        for (int i = 0; i < dresses.size(); i++) {
            if (dresses.get(i).getDress().getId() == dressId) {
                found = true;
                newODress.setId(dresses.get(i).getId()); // need to update this entity

                // replace old entity
                dresses.set(i, newODress);
                break;
            }
        }
        if (!found) {
            dresses.add(newODress);
        }

        if (!dress.getAvailableSizes().contains(dressSize)) {
            throw new DressNotFoundException("Size '" + dressSize + "' is not available");
        }

        basket.updateTotalPrice();

        return basketRepository.save(basket);
    }

    public Basket removeDress(Basket basket, long basketDressId) {
        List<OrderedDress> dresses = basket.getDresses();

        boolean founded = false;
        for (int i = 0; i < dresses.size(); i++) {
            if (dresses.get(i).getId() == basketDressId) {
                founded = true;
                dresses.remove(i);
                break;
            }
        }

        if (!founded) {
            throw new DressNotFoundException("Dress not found in your basket");
        }

        orderedDressRepository.deleteById(basketDressId); // because orphanRemoval is off
        basket.updateTotalPrice();

        return basketRepository.save(basket);
    }

    public Basket clearBasketAndDresses(Basket basket) {
        List<OrderedDress> dresses = basket.getDresses();

        while (!dresses.isEmpty()) {
            long id = dresses.get(0).getId();
            dresses.remove(0); // Should first be removed from session
            orderedDressRepository.deleteById(id);
        }
        basket.updateTotalPrice();

        return basketRepository.save(basket);
    }

    // Clear only foreign keys without removing dresses
    public Basket clearBasket(Basket basket) {
        List<OrderedDress> dresses = basket.getDresses();

        dresses.clear();
        basket.updateTotalPrice();

        return basketRepository.save(basket);
    }

}
