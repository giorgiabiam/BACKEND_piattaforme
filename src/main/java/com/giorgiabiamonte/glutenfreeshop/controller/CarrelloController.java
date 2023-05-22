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
            ProdottoAcquistato p = new ProdottoAcquistato();
            p.setProdottoReale(pReale);
            p.setQtaAcquistata(req.getQta());
            //salverò il prodotto acquistato nel DB solo quando l'utente farà l'acquisto
            carrello.getListaProdottiReal().add(pReale);
            carrello.incrementa();
            System.out.println("carrello dopo aggiunta---" +carrello.toString());
            return carrello;
        }
    }

    @DeleteMapping
    public Carrello clear(){
        carrello.getListaProdotti().clear();
        carrello.getListaProdottiReal().clear();
        carrello.setTotale(0);
        carrello.setNProdotti(0);
        System.out.println("svuotato carrello");
        return carrello;
    }

    @DeleteMapping("/{codice_prodotto}")
    public Carrello rimuovi_dal_carrello(@PathVariable("codice_prodotto")Integer codice_prodotto){
        if(prepo.existsByCodice(codice_prodotto)){
            ProdottoInMagazzino p = prepo.findByCodice(codice_prodotto);
            for(int i=0; i<carrello.getListaProdottiReal().size(); i++){
                if(carrello.getListaProdottiReal().get(i).getCodice() == codice_prodotto){
                    carrello.getListaProdottiReal().remove(i);
                    carrello.decrementa();
                }
            }
            return carrello;
        }
        return null;
    }
}
