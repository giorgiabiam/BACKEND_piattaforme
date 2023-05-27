package com.giorgiabiamonte.glutenfreeshop.controller;

import com.giorgiabiamonte.glutenfreeshop.models.entities.Acquisto;
import com.giorgiabiamonte.glutenfreeshop.models.entities.Utente;
import com.giorgiabiamonte.glutenfreeshop.services.AcquistoService;
import com.giorgiabiamonte.glutenfreeshop.services.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@CrossOrigin(origins="*") //TODO   "http://localhost:4200"



public class AdminController {
    @Autowired
    private UtenteService us;
    @Autowired
    private AcquistoService as;

    @GetMapping("/userlist")
    public ResponseEntity<List<Utente>> getUtenti(){
        List<Utente> utente = us.getAll();
        return new ResponseEntity<>(utente, HttpStatus.OK);
    }
    @GetMapping("/acquistilist")
    public ResponseEntity<List<Acquisto>> getAcquisti() {
        List<Acquisto> acquisti = as.getAll();
        return new ResponseEntity<>(acquisti, HttpStatus.OK);
    }


}
