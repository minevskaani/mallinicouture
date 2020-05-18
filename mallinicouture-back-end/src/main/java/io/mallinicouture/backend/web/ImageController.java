package io.mallinicouture.backend.web;

import io.mallinicouture.backend.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/")
    public ResponseEntity<?> putImage(@RequestParam MultipartFile file) {
        storageService.uploadFile(file);

        return new ResponseEntity<>("Done", HttpStatus.OK);
    }
}
