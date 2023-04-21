package com.giorgiabiamonte.glutenfreeshop.entities;

// import javax.persistence.*;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
    private String img; //url dell'immagine del prodotto

    @Column(name="descrizione")
    private String descrizione;
}
