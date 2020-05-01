package io.mallinicouture.backend.service;

import io.mallinicouture.backend.domain.Dress;
import io.mallinicouture.backend.domain.Image;
import io.mallinicouture.backend.repository.DressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DressService {

    @Autowired
    private DressRepository dressRepository;

    @Autowired
    private ImageService imageService;

    public Dress createDress(Dress dress) {
        /*
        // TODO: Add dress already exists exception
        if (dress.getMainImage() != null) {
            Image created = imageService.createImage(dress.getMainImage());
            dress.setMainImage(created);
        }

         */
        return dressRepository.save(dress);
    }

    public List<Dress> getAllDresses() {
        return dressRepository.findAll();
    }

}
