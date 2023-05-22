package com.giorgiabiamonte.glutenfreeshop.models;

import com.giorgiabiamonte.glutenfreeshop.models.entities.ProdottoAcquistato;
import com.giorgiabiamonte.glutenfreeshop.models.entities.ProdottoInMagazzino;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.LinkedList;
import java.util.List;

@Getter @Setter @ToString
@Component
//@SessionScope   //TODO
public class Carrello {
    private int nProdotti;
    private List<ProdottoAcquistato> listaProdotti;
    private double totale;

    //provo con prodotto reale
    private List<ProdottoInMagazzino> listaProdottiReal;


    public Carrello(){
        listaProdotti = new LinkedList<>();
        nProdotti = 0;

        listaProdottiReal = new LinkedList<>();
    }

    public void incrementa(){
        nProdotti++;
    }
    public void decrementa(){
        nProdotti--;
    }

    public List<ProdottoAcquistato> getListaProdotti() {
        return listaProdotti;
    }

    public double getTotale() {
        double res =0;
        for(int i=0; i<listaProdottiReal.size(); i++){
            res+= listaProdottiReal.get(i).getPrezzo();
        }
        totale = res;
        return totale;
    }
}
