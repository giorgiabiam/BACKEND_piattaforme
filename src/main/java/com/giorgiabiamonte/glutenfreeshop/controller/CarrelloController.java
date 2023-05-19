package com.giorgiabiamonte.glutenfreeshop.controller;

import com.giorgiabiamonte.glutenfreeshop.models.Carrello;
import com.giorgiabiamonte.glutenfreeshop.models.CarrelloRequest;
import com.giorgiabiamonte.glutenfreeshop.models.entities.ProdottoAcquistato;
import com.giorgiabiamonte.glutenfreeshop.models.entities.ProdottoInMagazzino;
import com.giorgiabiamonte.glutenfreeshop.repositories.ProdottoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/carrello")
@CrossOrigin(origins="*")
public class CarrelloController {
    @Autowired
    Carrello carrello;
    @Autowired
    ProdottoRepository prepo;

    @GetMapping("")
    public Carrello get(){
        System.out.println("get carrello");
        return carrello;
    }

    @PostMapping("")
    public Carrello add(@RequestBody CarrelloRequest req){
        System.out.println("carrello request" + req);
        if( !prepo.existsByCodice(req.getCodice_prodotto()) ){
            System.out.println("il prodotto non esiste");
            return null;
        }
        else{
            ProdottoInMagazzino pReale = prepo.findByCodice(req.getCodice_prodotto());
            ProdottoAcquistato p = new ProdottoAcquistato();
            p.setProdottoReale(pReale);
            p.setQtaAcquistata(req.getQta());
            //salverò il prodotto acquistato nel DB solo quando l'utente farà l'acquisto
            carrello.getListaProdotti().add(p);
            System.out.println("aggiunto al carrello il prodotto "+req.getCodice_prodotto());
            return carrello;
        }
    }

    @DeleteMapping("")
    public Carrello clear(){
        carrello.getListaProdotti().clear();
        carrello.setNProdotti(0);
        System.out.println("svuoto carrello");
        return carrello;
    }
}
