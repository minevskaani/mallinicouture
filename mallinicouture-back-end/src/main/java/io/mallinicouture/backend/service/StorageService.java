package io.mallinicouture.backend.service;

import io.mallinicouture.backend.exception.StorageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@Slf4j
public class StorageService {

    @Value("${image.path}")
    private String path;

    public void uploadFile(MultipartFile file) {
        InputStream is = null;
        String fileName = file.getOriginalFilename();

        if (file.isEmpty()) {
            throw new StorageException("File " + fileName + " is empty");
        }


        try {
            is = file.getInputStream();
        } catch (IOException e) {
            log.error("Can not get input stream from MultipartFile " + fileName, e);

            throw new StorageException("Can not save file: " + "fileName");
        }

        uploadFrom(is, fileName);
    }

    public void uploadFrom(InputStream is, String fileName) {
        try {
            Files.copy(is, Paths.get(path + fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            log.error("Can not copy file " + fileName + " from input stream", e);

            throw new StorageException("Can not save file: " + "fileName");
        }

    }
}
