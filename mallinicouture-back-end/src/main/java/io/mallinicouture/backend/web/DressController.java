package io.mallinicouture.backend.web;

import io.mallinicouture.backend.domain.Category;
import io.mallinicouture.backend.domain.Dress;
import io.mallinicouture.backend.service.DressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api/dresses")
public class DressController {

    @Autowired
    private DressService dressService;

    @GetMapping("")
    public ResponseEntity<?> getAllDresses(@RequestParam(name = "categoryId", required = false) Long catId) {
        List<Dress> dresses;
        if (catId == null) {
            dresses = dressService.getAllDresses();
        } else {
            Category category = dressService.getCategory(catId);

            dresses = dressService.getAllDressesByCategory(category);
        }

        return new ResponseEntity<>(dresses, HttpStatus.OK);
    }

    @GetMapping("/{dress_id}")
    public ResponseEntity<?> getDressById(@PathVariable(name = "dress_id") Long dId) {
        Dress dress = dressService.getById(dId);

        return new ResponseEntity<>(dress, HttpStatus.OK);
    }
}
