package com.giorgiabiamonte.glutenfreeshop.controller;

import com.giorgiabiamonte.glutenfreeshop.entities.Acquisto;
import com.giorgiabiamonte.glutenfreeshop.exception.QuantitaNonDisponibile;
import com.giorgiabiamonte.glutenfreeshop.services.AcquistoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/acquisti")
@CrossOrigin(origins="http://localhost:4200")
public class AcquistoController {

    @Autowired
    private AcquistoService as;

    @PostMapping("/new")
    public ResponseEntity<Acquisto> newAcquisto(Acquisto a){
        try{
//            Acquisto nuovoAcquisto= as.newAcquisto(a);
//            return new ResponseEntity<>(nuovoAcquisto, HttpStatus.CREATED);
        } catch (QuantitaNonDisponibile e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantità insufficiente per soddisfare la richiesta", e);
        }
        return null;
    }

    @GetMapping("/{idAcquirente}")
    public ResponseEntity<List<Acquisto>> acquistiUtente(@PathVariable("idAcquirente") int id){
        List<Acquisto> listaAcquisti= as.acquistiUtente(id);
        return new ResponseEntity<>(listaAcquisti, HttpStatus.OK);
    }
}
