package com.giorgiabiamonte.glutenfreeshop.models.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;

import java.util.List;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name="Utente")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", nullable=false)
    private Integer ID;

    @Column(name="username", nullable=false , unique = true)
    @Size(min = 2, max = 15)
    @NotBlank //equivale a not null e lunghezza dopo trim >0
    @Pattern(regexp = "") //TODO non si accettano spazi bianchi
    private String username;

    @Column(name="password", nullable=false)
    @Size(min = 4, max = 15)
    @NotBlank
    @Pattern(regexp = "") //TODO non si accettano spazi bianchi
    private String password;

    @Column(name="convenzionato", nullable=false)
    private boolean convenzionato;

    @Column(name="saldo")
    private double saldo;

    @Column(name="indirizzo", nullable = false)
    @NotBlank
    private String indirizzo;

    @Column(name="nome", nullable=false)
    @Size(min = 2, max = 15)
    @NotBlank
    private String nome;

    @Column(name="cognome", nullable=false)
    @Size(min = 2, max = 15)
    @NotBlank
    private String cognome;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "acquirente")
    private List<Acquisto> listaAcquisti;

    @OneToMany(fetch = FetchType.LAZY)
    private List<ProdottoInMagazzino> preferiti;
}
