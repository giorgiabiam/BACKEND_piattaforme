package com.giorgiabiamonte.glutenfreeshop.models.entities;

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

    @Column(name="qta_acquistata")
    private int qtaAcquistata;

    @ManyToOne
    @JoinColumn(name="prodotto_reale")
    @JsonIgnore
    private ProdottoInMagazzino prodottoReale;

//    @ManyToOne
//    @JoinTable(name = "Acquisto")
//    private Acquisto acquisto;
//
//    public Acquisto getAcquisto(){
//        return this.acquisto;
//    }
//    public void setAcquisto(){
//        this.acquisto=acquisto;
//    }
}
