package com.giorgiabiamonte.glutenfreeshop.models;


import lombok.Getter;

@Getter
public class LoginRequest {
    //ho creato questo modello che ha solo username e password per non usare il modello Utente
//   con tutti gli altri campi che sarebbero stati null
    private String username;
    private String password;
}
