package com.giorgiabiamonte.glutenfreeshop.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name="Prodotto_acquistato")
@Entity
public class ProdottoAcquistato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codice", nullable = false)
    private int codice;

    @Column(name="qta_acquistata")
    private int qtaAcquistata;

    @ManyToOne
    @JoinColumn(name="prodotto_reale")
    @JsonIgnore
    private ProdottoInMagazzino prodottoReale;

}
