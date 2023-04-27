package com.giorgiabiamonte.glutenfreeshop.controller;

import com.giorgiabiamonte.glutenfreeshop.models.entities.Utente;
import com.giorgiabiamonte.glutenfreeshop.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utenti")
@CrossOrigin(origins="*")
public class UtenteController {

    @Autowired
    private UtenteService us;

    @GetMapping("/all")
    public ResponseEntity<List<Utente>> getAll(){
        List<Utente> utenti= us.getAll();
        System.out.println("lista utenti" + utenti);
        return new ResponseEntity<>(utenti, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utente> getUtenteByID(@PathVariable("id") Integer id){
        Utente utente=us.findUtenteByID(id);
        return new ResponseEntity<>(utente, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Utente> newUser(@RequestBody Utente u){
        Utente nuovoUtente=us.newUser(u);
        return new ResponseEntity<>(nuovoUtente, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/login/{username}/{password}", method = RequestMethod.GET)
    public ResponseEntity<Utente> login(@PathVariable("username") String username, @PathVariable("password") String password){
        if(us.esisteUtente(username, password)){
            System.out.print("login ok");
            return new ResponseEntity<>(us.findUtenteByUsername(username), HttpStatus.OK);
        }
        System.out.println("login non riuscito");
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

}
