package io.mallinicouture.backend.service;

import io.mallinicouture.backend.domain.Advertisement;
import io.mallinicouture.backend.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementService {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    public List<Advertisement> getAllAdvertisements() {
        return advertisementRepository.findAll();
    }

    public Advertisement createOrUpdateAdvirtisement(Advertisement advertisement) {
        return advertisementRepository.save(advertisement);
    }
}
