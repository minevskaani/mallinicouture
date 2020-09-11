package io.mallinicouture.backend.repository;

import io.mallinicouture.backend.domain.Advertisement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementRepository extends CrudRepository<Advertisement, Long> {

    List<Advertisement> findAll();
}
