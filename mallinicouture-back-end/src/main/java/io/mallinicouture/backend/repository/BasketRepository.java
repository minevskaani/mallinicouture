package io.mallinicouture.backend.repository;

import io.mallinicouture.backend.domain.Basket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends CrudRepository<Basket, Long> {

    Basket getById(Long id);
    Basket findByClient(String username);

}
