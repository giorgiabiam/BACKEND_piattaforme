package com.giorgiabiamonte.glutenfreeshop.controller;

import com.giorgiabiamonte.glutenfreeshop.models.Carrello;
import com.giorgiabiamonte.glutenfreeshop.models.CarrelloItem;
import com.giorgiabiamonte.glutenfreeshop.models.CarrelloRequest;
import com.giorgiabiamonte.glutenfreeshop.models.entities.ProdottoInMagazzino;
import com.giorgiabiamonte.glutenfreeshop.repositories.ProdottoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        if( !prepo.existsByCodice(req.getCodice_prodotto()) ){
            System.out.println("il prodotto non esiste");
            return null;
        }
        else{
            ProdottoInMagazzino pReale = prepo.findByCodice(req.getCodice_prodotto());

            if( !carrello.getMap().containsKey(req.getCodice_prodotto()) ){
                carrello.getListaProdottiReal().add(pReale);
                //questa lista deve contenere una sola occorrenza dello stesso prodotto
            }

            carrello.incrementa(pReale.getCodice(), req.getQta());
            System.out.println("carrello dopo aggiunta---" +carrello.toString());
            System.out.println("map dopo aggiunta---" +carrello.getMap().toString());
            carrello.setTotale(calcolaTot(carrello.getMap()));

            return carrello;
        }
    }

    @PostMapping(value = "/aggiungicarrelloitem")
    public Carrello add_nuova(@RequestBody CarrelloRequest req){
        System.out.println("carrello request" + req.toString());
        if( !prepo.existsByCodice(req.getCodice_prodotto()) ){
            System.out.println("il prodotto non esiste");
            return null;
        }
        else{
            ProdottoInMagazzino pReale = prepo.findByCodice(req.getCodice_prodotto());
            //int index =
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
            System.out.println("ssssssssssssssssssssssssssssssssssssss"+item1);
//            if( index != -1){
//                CarrelloItem item = carrello.getLista().get(index);
//                System.out.println("---" + item);
//                item.setQta_acquist(req.getQta());
//            }
//            else{
//                CarrelloItem item = new CarrelloItem();
//                item.setProdotto(pReale);
//                item.setQta_acquist(req.getQta());
//                carrello.getLista().add(item);
//            }
            //incrementa totale
            carrello.setTotale( calcolaTot_nuovo(carrello.getLista()) );

            System.out.println("carrello dopo aggiunta nuova---" +carrello.toString());
            carrello.setTotale(calcolaTot(carrello.getMap()));

            return carrello;
        }

    }

    @DeleteMapping
    public Carrello clear(){
        System.out.println("DELETE");
        carrello.getMap().clear();
        carrello.getListaProdottiReal().clear();
        carrello.setTotale(0);
        System.out.println("svuotato carrello");
        return carrello;
    }

    @DeleteMapping("/{codice_prodotto}")
    public Carrello rimuovi_dal_carrello(@PathVariable("codice_prodotto")Integer codice_prodotto){
        if(prepo.existsByCodice(codice_prodotto)){
            carrello.getMap().remove(codice_prodotto);

            for(int i =0; i<carrello.getListaProdottiReal().size(); i++){
                if(carrello.getListaProdottiReal().get(i).getCodice() == codice_prodotto)
                    carrello.getListaProdottiReal().remove(i);
            }

            carrello.decrementa(codice_prodotto); //TODO gestire quantità da rimuovere o rimuovere tutti?
            carrello.setTotale( calcolaTot(carrello.getMap()) );

            System.out.println("cart dopo rimozione---" + carrello);

            return carrello;
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

    public double calcolaTot_nuovo(List<CarrelloItem> lista) {
        double res =0d;
        for(CarrelloItem it: lista){
            res += it.getProdotto().getPrezzo() * it.getQta_acquist();
        }
        return res;
    }

}
