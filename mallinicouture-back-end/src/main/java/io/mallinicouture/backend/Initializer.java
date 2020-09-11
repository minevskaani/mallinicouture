package io.mallinicouture.backend;

import io.mallinicouture.backend.domain.*;
import io.mallinicouture.backend.repository.AdvertisementRepository;
import io.mallinicouture.backend.repository.ImageRepository;
import io.mallinicouture.backend.service.AdvertisementService;
import io.mallinicouture.backend.service.DressService;
import io.mallinicouture.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Initializer implements CommandLineRunner {

    ///*
    @Autowired
    private DressService dressService;
    @Autowired
    private UserService userService;
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private AdvertisementService advertisementService;

    @Override
    public void run(String... args) throws Exception {
        final String BASE_URL = "http://192.168.1.4:8080";
        ///*
        // Images
        Image catMini = imageRepository.save(new Image(null, BASE_URL + "/image/2.png", null));
        Image catLong  = imageRepository.save(new Image(null, BASE_URL + "/image/5.png", null));
        Image catOfficial  = imageRepository.save(new Image(null, BASE_URL + "/image/14.png", null));

        Image[] img = new Image[36];

        for (int i = 1; i < img.length; i++) {
            img[i]  = imageRepository.save(new Image(null, BASE_URL + "/image/" + i + ".png", null));
        }

        // Categories
        Category cat1 = dressService.addCategory(new Category(null, "Длинные", img[5], null));
        Category cat2 = dressService.addCategory(new Category(null, "Мини", img[2], null));
        Category cat3 = dressService.addCategory(new Category(null, "Официальные", img[14], null));

        // Dresses
        dressService.saveDress(new Dress("Title 1", img[1], 99.99f, DressSize.XL, DressSize.L), cat1);
        dressService.saveDress(new Dress("Title 2", img[2], 59.99f,  DressSize.XXS), cat1);
        dressService.saveDress(new Dress("Title 3", img[3], 12f, DressSize.L, DressSize.L), cat2);
        dressService.saveDress(new Dress("Title 4", img[4], 11), cat3);

        // Advertisements
        advertisementService.createOrUpdateAdvirtisement(new Advertisement(null, "Новые платья", "30+ дизайнерских брендов", img[14]));
        advertisementService.createOrUpdateAdvirtisement(new Advertisement(null, "Новые платья", "30+ дизайнерских брендов", img[15]));
        advertisementService.createOrUpdateAdvirtisement(new Advertisement(null, "Новые платья", "30+ дизайнерских брендов", img[34]));

        // Test user
        Client testUser = userService.saveClient(new Client(null, "test@gmail.com", "Jone", "Doe", "password", "", null, new Basket(), null, null, null));

       // orderService.makeOrder(testUser.getUsername(), "test address", false);

    }

}
