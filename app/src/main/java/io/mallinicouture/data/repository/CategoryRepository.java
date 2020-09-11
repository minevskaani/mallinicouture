package io.mallinicouture.data.repository;

import java.util.List;

import javax.inject.Inject;

import io.mallinicouture.data.model.Category;
import io.mallinicouture.data.remote.CategoryService;
import io.reactivex.rxjava3.core.Single;

public class CategoryRepository {

    private final CategoryService categoryService;

    @Inject
    public CategoryRepository(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Single<List<Category>> getAllCategories() {
        return categoryService.getAllCategories();
    }

}
