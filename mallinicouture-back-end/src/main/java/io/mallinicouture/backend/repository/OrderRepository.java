package io.mallinicouture.backend.repository;

import io.mallinicouture.backend.domain.Client;
import io.mallinicouture.backend.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findAll();
    List<Order> findAllByClient(Client client);

}
