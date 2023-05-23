package com.giorgiabiamonte.glutenfreeshop.controller;

import com.giorgiabiamonte.glutenfreeshop.models.Carrello;
import com.giorgiabiamonte.glutenfreeshop.models.entities.Acquisto;
import com.giorgiabiamonte.glutenfreeshop.utils.exception.QuantitaNonDisponibile;
import com.giorgiabiamonte.glutenfreeshop.services.AcquistoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/acquisti")
@CrossOrigin(origins="*")  //TODO
public class AcquistoController {

    @Autowired
    private AcquistoService as;

    @PostMapping("/{idAcquirente}")
    public ResponseEntity<Acquisto> newAcquisto(@PathVariable("idAcquirente") int idAcquirente, @RequestBody Carrello carrello){
        System.out.println("CARRELLO in acquisto controller" + carrello);
        try{
            Acquisto nuovoAcquisto = as.newAcquisto(carrello, idAcquirente);
            return new ResponseEntity<>(nuovoAcquisto, HttpStatus.CREATED);
        } catch (QuantitaNonDisponibile e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Acquisto non andato a buon fine", e);
        }
    }

    @GetMapping("/{idAcquirente}")
    public ResponseEntity<List<Acquisto>> getAcquistiUtente(@PathVariable("idAcquirente") int idAcquirente){
        List<Acquisto> listaAcquisti= as.getAcquistiUtente(idAcquirente);
        return new ResponseEntity<>(listaAcquisti, HttpStatus.OK);
    }
}
