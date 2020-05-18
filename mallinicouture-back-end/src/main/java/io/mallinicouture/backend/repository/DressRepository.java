package io.mallinicouture.backend.repository;

import io.mallinicouture.backend.domain.Category;
import io.mallinicouture.backend.domain.Dress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DressRepository extends JpaRepository<Dress, Long> {

    List<Dress> findAll();
    List<Dress> findAllByCategory(Category category);

    Dress getById(Long id);
}
