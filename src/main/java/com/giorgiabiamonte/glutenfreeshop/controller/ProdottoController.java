package com.giorgiabiamonte.glutenfreeshop.controller;

import com.giorgiabiamonte.glutenfreeshop.models.entities.ProdottoInMagazzino;
import com.giorgiabiamonte.glutenfreeshop.services.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prodotti")
@CrossOrigin(origins="http://localhost:4200")
public class ProdottoController {

    @Autowired
    private ProdottoService ps;

    @GetMapping("/all")
    public ResponseEntity<List<ProdottoInMagazzino>> getAll(){
        List<ProdottoInMagazzino> prodotti= ps.getAll();
        return new ResponseEntity<>(prodotti, HttpStatus.OK);
    }

    //@Version
    //@JsonIgnore
    //private long version  //per l'accesso concorrente

}
