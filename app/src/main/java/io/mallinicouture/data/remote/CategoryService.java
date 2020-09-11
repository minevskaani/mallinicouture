package io.mallinicouture.data.remote;

import java.util.List;

import io.mallinicouture.data.model.Category;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface CategoryService {

    @GET("/api/categories")
    Single<List<Category>> getAllCategories();

}
