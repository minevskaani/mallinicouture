package io.mallinicouture.backend.controller;

import io.mallinicouture.backend.service.DressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dresses")
public class DressController {

    @Autowired
    private DressService dressService;

    @GetMapping("/")
    public ResponseEntity<?> getAllDresses() {
        return new ResponseEntity<>(dressService.getAllDresses(), HttpStatus.OK);
    }
}
