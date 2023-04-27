package com.giorgiabiamonte.glutenfreeshop.models.entities;

import lombok.*;
import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;

import java.util.List;

@Data
@Entity
@Table(name="Utente")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", nullable=false)
    private Integer ID;

    @Column(name="username", nullable=false)
    private String username;

    @Column(name="password", nullable=false)
    private String password;

    @Column(name="convenzionato", nullable=false)
    private boolean convenzionato;

    @Column(name="saldo")
    private double saldo;

    @Column(name="indirizzo", nullable = false)
    private String indirizzo;

    @Column(name="nome", nullable=false)
    private String nome;

    @Column(name="cognome", nullable=false)
    private String cognome;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "acquirente")
    private List<Acquisto> listaAcquisti;

    @OneToMany(fetch = FetchType.EAGER)
    private List<ProdottoInMagazzino> preferiti;
}