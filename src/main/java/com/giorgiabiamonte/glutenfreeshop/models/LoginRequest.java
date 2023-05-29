package com.giorgiabiamonte.glutenfreeshop.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public class LoginRequest {
    //ho creato questo modello che ha solo username e password per non usare il modello Utente
//   con tutti gli altri campi che sarebbero stati null
    private String username;
    private String password;
}
