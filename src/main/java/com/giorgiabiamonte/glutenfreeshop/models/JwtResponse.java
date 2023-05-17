package com.giorgiabiamonte.glutenfreeshop.models;

import com.giorgiabiamonte.glutenfreeshop.models.entities.Utente;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtResponse {
    private String token;
    private Utente utente;
}
