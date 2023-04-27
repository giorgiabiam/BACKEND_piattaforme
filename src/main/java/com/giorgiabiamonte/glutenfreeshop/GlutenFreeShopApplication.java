package com.giorgiabiamonte.glutenfreeshop;

import com.giorgiabiamonte.glutenfreeshop.ProveServlet.ProvaServlet;
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

    @Bean
    ServletRegistrationBean myServletRegistration () {
        ServletRegistrationBean srb = new ServletRegistrationBean();
        srb.setServlet(new ProvaServlet());
        srb.setUrlMappings(Arrays.asList("/path2/*"));
        return srb;
    }

    public static void main(String[] args) {
        SpringApplication.run(GlutenFreeShopApplication.class, args);
    }

}
