package io.mallinicouture.backend;

import io.mallinicouture.backend.domain.Dress;
import io.mallinicouture.backend.domain.DressSize;
import io.mallinicouture.backend.domain.Image;
import io.mallinicouture.backend.service.DressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendApplication {

    ///*
    @Autowired
    DressService dressService;

    @Bean
    CommandLineRunner init() {
        return args -> {
            ///*
            dressService.createDress(new Dress("Title 1", new Image("Some path"), DressSize.XL, DressSize.L));
            dressService.createDress(new Dress("Title 2", new Image("Another path"), DressSize.XXS));
            dressService.createDress(new Dress("Title 3", new Image("Some path"), DressSize.L, DressSize.L));
            dressService.createDress(new Dress("Title 4", null));
            // */
        };
    }
     //*/

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

}
