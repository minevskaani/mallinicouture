package io.mallinicouture.data.repository;

import java.util.List;

import javax.inject.Inject;

import io.mallinicouture.data.model.Advertisement;
import io.mallinicouture.data.remote.AdvertisementService;
import io.reactivex.rxjava3.core.Single;

public class AdvertisementRepository {

    private final AdvertisementService advertisementService;

    @Inject
    public AdvertisementRepository(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    public Single<List<Advertisement>> getAllAdvertisements() {
        return advertisementService.getAllAdvertisements();
    }
}
