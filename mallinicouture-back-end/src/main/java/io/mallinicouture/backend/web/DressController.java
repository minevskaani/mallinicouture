package io.mallinicouture.backend.web;

import io.mallinicouture.backend.domain.Category;
import io.mallinicouture.backend.domain.Dress;
import io.mallinicouture.backend.service.DressService;
import io.mallinicouture.backend.service.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/dresses")
public class DressController {

    @Autowired
    private DressService dressService;
    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    public ResponseEntity<?> saveDress(@RequestBody @Valid Dress dress, BindingResult dressBindingResult) {
        if (dressBindingResult.hasErrors()) return mapValidationErrorService.doMapping(dressBindingResult);

        return ResponseEntity.ok(dressService.saveDress())
    }

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
