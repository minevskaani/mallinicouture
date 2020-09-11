package io.mallinicouture.backend.web;

import io.mallinicouture.backend.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/advertisement")
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    @GetMapping("")
    public ResponseEntity<?> getAllAdvertisements() {
        return new ResponseEntity<>(advertisementService.getAllAdvertisements(), HttpStatus.OK);
    }
}
