package com.giorgiabiamonte.glutenfreeshop.entities;

// import javax.persistence.*;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Acquisto {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Acquisto acquisto = (Acquisto) o;
        return ID == acquisto.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", nullable=false)
    private int ID;

    @Column(name="data_acquisto", nullable = false)
    private String dataAcquisto;

    @Transient
    private double tot;

    public double getTot(){
        double tot=0d;
        List<ProdottoAcquistato> prodotti=getListaProdotti();
        for(ProdottoAcquistato p : prodotti){
            tot += p.getQtaAcquistata() * p.getProdottoReale().getPrezzo();
        }
        return tot;
    }

    @Column(name="IDacquirente", nullable=false)
    private int IDacquirente;   //non serve posso ottenerlo

    //più acquisti per lo stesso acquirente
    private Utente acquirente;

    @ManyToOne
    @JoinTable(name="utente")
    public Utente getAcquirente(){
        return acquirente;
    }
    public void setAcquirente(Utente acquirente){
        this.acquirente=acquirente;
    }

    @OneToMany(mappedBy = "acquisto")
    private List<ProdottoAcquistato> listaProdotti;
}
