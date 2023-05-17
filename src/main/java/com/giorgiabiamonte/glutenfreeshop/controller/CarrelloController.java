package com.giorgiabiamonte.glutenfreeshop.controller;

import com.giorgiabiamonte.glutenfreeshop.models.Carrello;
import com.giorgiabiamonte.glutenfreeshop.models.entities.ProdottoAcquistato;
import com.giorgiabiamonte.glutenfreeshop.repositories.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/carrello")
@CrossOrigin(origins="*")
public class CarrelloController {
    @Autowired
    Carrello carrello;

    @Autowired
    ProdottoRepository prepo;

    @GetMapping
    public Carrello get(){
        System.out.println("get carrello");
        return carrello;
    }

    @PostMapping
    public Carrello add(@RequestBody Integer codice_prodotto){ //deve ricevere anche la quantià
        System.out.println("add "+codice_prodotto+" al carrello");
        if( !prepo.existsById(codice_prodotto) ){
            return null;
        }
        else{
            ProdottoAcquistato p = prepo.findByCodice(codice_prodotto);
            carrello.getListaProdotti().add(p);
            return carrello;
        }
    }
}
