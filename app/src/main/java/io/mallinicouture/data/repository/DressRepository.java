package io.mallinicouture.data.repository;

import java.util.List;

import javax.inject.Inject;

import io.mallinicouture.data.model.Dress;
import io.mallinicouture.data.remote.DressService;
import io.reactivex.rxjava3.core.Single;

public class DressRepository {

    private final DressService dressService;

    @Inject
    public DressRepository(DressService dressService) {
        this.dressService = dressService;
    }


    public Single<List<Dress>> getAllDresses() {
        return dressService.getAllDresses();
    }

    public Single<List<Dress>> getAllDressesByCatId(Long categoryId) {
        return dressService.getAllDressesByCatId(categoryId);
    }

    public Single<Dress> getDressById(Long dressId) {
        return dressService.getDressById(dressId);
    }

}
