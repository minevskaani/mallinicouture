package io.mallinicouture.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageService {

    @Value("${image.path}")
    private String path;

    public void uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            // TODO: StorageException
        }

        try {
            InputStream is = file.getInputStream();
            String fileName = file.getOriginalFilename();

            Files.copy(is, Paths.get(path + fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            // TODO
            System.out.println("error");
        }
    }
}
