package io.mallinicouture.backend.repository;

import io.mallinicouture.backend.domain.Dress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DressRepository extends CrudRepository<Dress, Long> {

    List<Dress> findAll();
}
