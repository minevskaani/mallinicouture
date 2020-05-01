package io.mallinicouture.backend.service;

import io.mallinicouture.backend.domain.Image;
import io.mallinicouture.backend.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private StorageService storageService;

    public Image createImage(Image image, MultipartFile file) {
        return imageRepository.save(image);
    }

    public void getImage(@Valid Image image) {

    }
}
