package io.mallinicouture.backend.web;

import io.mallinicouture.backend.domain.Category;
import io.mallinicouture.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
