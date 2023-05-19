package com.giorgiabiamonte.glutenfreeshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@SpringBootApplication
@RestController
public class GlutenFreeShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(GlutenFreeShopApplication.class, args);
    }

}
