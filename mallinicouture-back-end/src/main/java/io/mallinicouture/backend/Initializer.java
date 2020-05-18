package io.mallinicouture.backend;

import io.mallinicouture.backend.domain.*;
import io.mallinicouture.backend.repository.ImageRepository;
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

    @Override
    public void run(String... args) throws Exception {
        ///*
        // Images
        Image image1 = imageRepository.save(new Image(null, "http://localhost:8080/image/ani.jpg", null));
        System.out.println(image1.toString());

        // Categories
        Category cat1 = dressService.addCategory(new Category(null, "Cat 1", image1, null));
        Category cat2 = dressService.addCategory(new Category(null, "Cat 2", image1, null));
        Category cat3 = dressService.addCategory(new Category(null, "Cat 3", image1, null));

        // Dresses
        dressService.saveDress(new Dress("Title 1", image1, 99.99f, DressSize.XL, DressSize.L), cat1);
        dressService.saveDress(new Dress("Title 2", image1, 59.99f,  DressSize.XXS), cat1);
        dressService.saveDress(new Dress("Title 3", image1, 12f, DressSize.L, DressSize.L), cat2);
        dressService.saveDress(new Dress("Title 4", image1, 11), cat3);

        // */
        // Test user
        Client testUser = userService.saveClient(new Client(null, "test@gmail.com", "Jone", "Doe", "password", "", null, new Basket(), null, null, null));

       // orderService.makeOrder(testUser.getUsername(), "test address", false);

    }

}
