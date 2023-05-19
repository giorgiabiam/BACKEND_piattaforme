package com.giorgiabiamonte.glutenfreeshop.controller;

import com.giorgiabiamonte.glutenfreeshop.models.entities.ProdottoInMagazzino;
import com.giorgiabiamonte.glutenfreeshop.services.ProdottoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prodotti")
@Slf4j
@CrossOrigin(origins="*")  //TODO
public class ProdottoController {

    @Autowired
    private ProdottoService ps;

    @GetMapping
    public ResponseEntity<List<ProdottoInMagazzino>> getAll(){
        log.info("GET ALL PRODOTTI");
        List<ProdottoInMagazzino> prodotti= ps.getAll();
        return new ResponseEntity<>(prodotti, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdottoInMagazzino> getById(@PathVariable("id") Integer codice){
        log.info("GET del prodotto "+codice);
        ProdottoInMagazzino p = ps.get(codice);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

}
