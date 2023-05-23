package com.giorgiabiamonte.glutenfreeshop.models;

import com.giorgiabiamonte.glutenfreeshop.models.entities.ProdottoInMagazzino;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.*;

@Getter @Setter @ToString
@Component
//@SessionScope   //TODO ?
public class Carrello {
    private double totale;
    private Map<Integer, Integer> map; // <codice prodotto, quantità>
    private List<ProdottoInMagazzino> listaProdottiReal;

    public Carrello(){
        totale = 0d;
        map = new HashMap<>();
        listaProdottiReal = new LinkedList<>();
    }

    public void incrementa(int codice_prodotto, int qta) {
        //TODO prezzo totale

        if(map.containsKey(codice_prodotto)){
            int nuovaQta = qta + map.get(codice_prodotto);
            map.replace(codice_prodotto, nuovaQta);
        }
        else{
            map.put(codice_prodotto, qta);
        }
    }

    public void decrementa(int codice_prodotto){
        map.remove(codice_prodotto);
        //TODO prezzo totale
    }

}
