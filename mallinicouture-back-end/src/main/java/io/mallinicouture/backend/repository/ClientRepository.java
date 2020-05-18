package io.mallinicouture.backend.repository;

import io.mallinicouture.backend.domain.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    List<Client> findAll();

    Client getById(Long id);
    Client findByUsername(String username);
}
