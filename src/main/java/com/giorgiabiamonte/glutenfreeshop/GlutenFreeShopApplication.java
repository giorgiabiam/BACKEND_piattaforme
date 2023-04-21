package com.giorgiabiamonte.glutenfreeshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GlutenFreeShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlutenFreeShopApplication.class, args);
        System.out.println("FUNZIONA");
    }

    @GetMapping
    public String prova(){
        return "prova";
    }

}
