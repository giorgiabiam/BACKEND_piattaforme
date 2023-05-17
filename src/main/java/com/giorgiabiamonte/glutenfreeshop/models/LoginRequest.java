package com.giorgiabiamonte.glutenfreeshop.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequest {
    //ho creato questo modello che ha solo username e password per non usare il modello Utente
//   con tutti gli altri campi che sarebbero stati null

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
