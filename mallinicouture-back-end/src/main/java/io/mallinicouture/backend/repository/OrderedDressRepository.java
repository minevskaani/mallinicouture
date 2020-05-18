package io.mallinicouture.backend.repository;

import io.mallinicouture.backend.domain.OrderedDress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedDressRepository extends CrudRepository<OrderedDress, Long> {

}
