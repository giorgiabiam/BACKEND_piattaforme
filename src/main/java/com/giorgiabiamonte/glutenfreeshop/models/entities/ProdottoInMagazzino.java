package com.giorgiabiamonte.glutenfreeshop.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor  @NoArgsConstructor
@Entity
@Table(name="Prodotto")
public class ProdottoInMagazzino {


    @Id
    @Column(name="codice", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codice;

    @Column(name="nome", nullable=false)
    private String nome;

    @Column(name="prezzo", nullable=false)
    private double prezzo;

    @Column(name="qta", nullable=false)
    private int qta;

    @Column(name="img")
    private String img;

    @Column(name="descrizione")
    private String descrizione;

    @Version
    private int version;


}
