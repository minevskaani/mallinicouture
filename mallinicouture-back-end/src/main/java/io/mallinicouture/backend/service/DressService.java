package io.mallinicouture.backend.service;

import io.mallinicouture.backend.domain.Category;
import io.mallinicouture.backend.domain.Dress;
import io.mallinicouture.backend.exception.CategoryNotFoundException;
import io.mallinicouture.backend.exception.DressNotFoundException;
import io.mallinicouture.backend.repository.CategoryRepository;
import io.mallinicouture.backend.repository.DressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DressService {

    @Autowired
    private DressRepository dressRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImageService imageService;

    public Dress saveDress(Dress dress, Category category) {
        dress.setCategory(category);
        /*
        // TODO: add dress already exists exception?
        if (dress.getMainImage() != null) {
            Image created = imageService.createImage(dress.getMainImage());
            dress.setMainImage(created);
        }
         */
        return dressRepository.save(dress);
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Dress getById(Long id) {
        return dressRepository
                .findById(id)
                .orElseThrow(() -> new DressNotFoundException("Dress with id '" + id + "' does not exists"));
    }

    public List<Dress> getAllDresses() {
        return dressRepository.findAll();
    }

    public List<Dress> getAllDressesByCategory(Category category) {
        return dressRepository.findAllByCategory(category);
    }

    public Category getCategory(Long catId) {
        return categoryRepository
                .findById(catId)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id '" + catId + "' not found"));
    }
}
