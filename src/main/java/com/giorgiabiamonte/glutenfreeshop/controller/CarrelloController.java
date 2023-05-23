package com.giorgiabiamonte.glutenfreeshop.controller;

import com.giorgiabiamonte.glutenfreeshop.models.Carrello;
import com.giorgiabiamonte.glutenfreeshop.models.CarrelloRequest;
import com.giorgiabiamonte.glutenfreeshop.models.entities.ProdottoAcquistato;
import com.giorgiabiamonte.glutenfreeshop.models.entities.ProdottoInMagazzino;
import com.giorgiabiamonte.glutenfreeshop.repositories.ProdottoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/carrello")
@CrossOrigin(origins="*")  //TODO
public class CarrelloController {
    @Autowired
    Carrello carrello;
    @Autowired
    ProdottoRepository prepo;

    @GetMapping
    public Carrello get(){
        return carrello;
    }

    @PostMapping
    public Carrello add(@RequestBody CarrelloRequest req){
        if( !prepo.existsByCodice(req.getCodice_prodotto()) ){
            System.out.println("il prodotto non esiste");
            return null;
        }
        else{
            ProdottoInMagazzino pReale = prepo.findByCodice(req.getCodice_prodotto());

            carrello.getListaProdottiReal().add(pReale);
//            carrello.getMap().put(req.getCodice_prodotto(), req.getQta());
            carrello.incrementa(pReale.getCodice(), req.getQta());
            System.out.println("carrello dopo aggiunta---" +carrello.toString());
            System.out.println("map dopo aggiunta---" +carrello.getMap().toString());
            carrello.setTotale(calcolaTot(carrello.getMap()));

            return carrello;
        }
    }

    @DeleteMapping
    public Carrello clear(){
        carrello.getMap().clear();
        carrello.getListaProdottiReal().clear();
        carrello.setTotale(0);
        System.out.println("svuotato carrello");
        return carrello;
    }

    @DeleteMapping("/{codice_prodotto}")
    public Carrello rimuovi_dal_carrello(@PathVariable("codice_prodotto")Integer codice_prodotto){
        if(prepo.existsByCodice(codice_prodotto)){
            ProdottoInMagazzino p = prepo.findByCodice(codice_prodotto);
            for(int i : carrello.getMap().keySet()){
                if( i == codice_prodotto ){
                    carrello.getMap().remove(i);
                    carrello.getListaProdottiReal().remove(p);
                    carrello.decrementa(codice_prodotto); //TODO gestire quantità da rimuovere o rimuovi tutti?
                    return carrello;
                }
            }
        }
        return null;
    }

    public double calcolaTot(Map<Integer, Integer> map) {
        double res =0d;
        for(int i : map.keySet()){
            ProdottoInMagazzino p = prepo.findByCodice(i);
            res+= p.getPrezzo()*map.get(i);
        }
        return res;
    }

}
