package com.giorgiabiamonte.glutenfreeshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Getter
@Setter
@Table(name="Prodotto_acquistato")
@Entity
public class ProdottoAcquistato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codice", nullable = false)
    private int codice;

    @Column(name="idprodotto_reale")
    private int IDprodottoReale;   //non serve posso ottenerlo

    @Column(name="qta_acquistata")
    private int qtaAcquistata;

    @ManyToOne
    // @JoinColumn(name="prodotto")
    @JsonIgnore
    private ProdottoInMagazzino prodottoReale;

    @ManyToOne
    @JsonIgnore
    @JoinTable(name="acquisto")
    private Acquisto acquisto;
}
