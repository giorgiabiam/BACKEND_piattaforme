package com.giorgiabiamonte.glutenfreeshop.utils.jwt;

import com.giorgiabiamonte.glutenfreeshop.models.entities.Utente;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtResponse {
    private String token;
    private Integer IDutente;
}
