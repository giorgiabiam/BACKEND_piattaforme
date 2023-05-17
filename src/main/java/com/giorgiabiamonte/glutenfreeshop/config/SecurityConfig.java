package com.giorgiabiamonte.glutenfreeshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers("/**").permitAll()
                .requestMatchers("/prodotti").permitAll()
                .requestMatchers("/acquisti/**").authenticated(); //manca filtro jwt

        http.csrf().disable();
        return http.build();
    }

   //repo github esercitatore
  //https://github.com/Franco7Scala/SpringProjectPSW/blob/master/src/main/java/it/frankladder/fakestore/configurations/SecurityConfiguration.java
}
