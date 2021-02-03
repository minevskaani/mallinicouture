package io.mallinicouture.backend.web;

import io.mallinicouture.backend.domain.Category;
import io.mallinicouture.backend.service.CategoryService;
import io.mallinicouture.backend.service.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping
    public ResponseEntity<?> saveCategory(@RequestBody @Valid Category category, BindingResult catBingingResult) {
        if (catBingingResult.hasErrors()) return mapValidationErrorService.doMapping(catBingingResult);

        return ResponseEntity.ok(categoryService.saveCategory(category));
    }

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
