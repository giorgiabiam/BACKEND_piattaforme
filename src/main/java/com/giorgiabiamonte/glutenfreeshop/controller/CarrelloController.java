package com.giorgiabiamonte.glutenfreeshop.controller;

import com.giorgiabiamonte.glutenfreeshop.models.Carrello;
import com.giorgiabiamonte.glutenfreeshop.models.CarrelloItem;
import com.giorgiabiamonte.glutenfreeshop.models.CarrelloRequest;
import com.giorgiabiamonte.glutenfreeshop.models.entities.ProdottoInMagazzino;
import com.giorgiabiamonte.glutenfreeshop.models.entities.Utente;
import com.giorgiabiamonte.glutenfreeshop.repositories.ProdottoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
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
        System.out.println("carrello request" + req.toString());
        if( !prepo.existsByCodice(req.getCodice_prodotto()) ){
            System.out.println("il prodotto non esiste");
            return null;
        }
        else{

            ProdottoInMagazzino pReale = prepo.findByCodice(req.getCodice_prodotto());

            CarrelloItem item1=carrello.getLista().stream().filter(x->{
                boolean b = x.getProdotto().getCodice() == req.getCodice_prodotto();
                return b;
            } ).findFirst().orElseGet(()->{
                CarrelloItem item = new CarrelloItem();
                item.setProdotto(pReale);
                item.setQta_acquist(req.getQta());
                carrello.getLista().add(item);

                return item;
            });
            item1.setQta_acquist(req.getQta());
            carrello.setTotale( calcolaTot_nuovo(carrello.getLista()) );
            System.out.println("carrello dopo aggiunta nuova---" +carrello.toString());
            return carrello;
        }

    }

    @DeleteMapping
    public Carrello clear(){
        carrello.setTotale(0);
        carrello.getLista().clear();
        return carrello;
    }

    @DeleteMapping("/{codice_prodotto}")
    public ResponseEntity<Carrello> rimuovi_dal_carrello(@PathVariable("codice_prodotto")Integer codice_prodotto){
        if(prepo.existsByCodice(codice_prodotto)){
//            for(CarrelloItem item: carrello.getLista()){
//                if(item.getProdotto().getCodice() == codice_prodotto){
//                    carrello.getLista().remove(item);
//                }
//            }

            for (Iterator<CarrelloItem> iterator = carrello.getLista().iterator(); iterator.hasNext();) {
                CarrelloItem item = iterator.next();
                if(item.getProdotto().getCodice() == codice_prodotto) {
                    iterator.remove();
                }
            }

            carrello.setTotale( calcolaTot_nuovo(carrello.getLista()) );
            System.out.println("cart dopo rimozione---" + carrello);
            return new ResponseEntity(carrello, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

    public double calcolaTot_nuovo(List<CarrelloItem> lista) {
        System.out.println("calcola tot");
        double res =0d;
        for(CarrelloItem it: lista){
            res += it.getProdotto().getPrezzo() * it.getQta_acquist();
        }
        return res;
    }

}
