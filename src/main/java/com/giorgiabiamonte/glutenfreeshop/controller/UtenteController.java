package com.giorgiabiamonte.glutenfreeshop.controller;

import com.giorgiabiamonte.glutenfreeshop.models.entities.ProdottoInMagazzino;
import com.giorgiabiamonte.glutenfreeshop.models.entities.Utente;
import com.giorgiabiamonte.glutenfreeshop.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utenti")
@CrossOrigin(origins="*") //TODO   "http://localhost:4200"
public class UtenteController {

    @Autowired
    private UtenteService us;

    @GetMapping("/")
    public ResponseEntity<List<Utente>> getAll(){
        List<Utente> utenti= us.getAll();
        return new ResponseEntity<>(utenti, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utente> getUtenteByID(@PathVariable("id") Integer id){
        Utente utente = us.findByID(id);
        return new ResponseEntity<>(utente, HttpStatus.OK);
    }


    @PostMapping(value = "/login")
    public ResponseEntity<Utente> login(@RequestBody Utente userData){
//      TODO usare un Encoder per la password
//      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//      String encodedPassword = passwordEncoder.encode(userData.getPassword());

        Utente u = us.login(userData.getUsername(), userData.getPassword()); //encodedPassword
        if(u==null){
            System.out.println("login non riuscito");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else{
            System.out.println("login riuscito per "+ u.getNome());
            return new ResponseEntity<>(u, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<Utente> registrazione(@RequestBody Utente nuovoUtente){
        Utente u = us.signin(nuovoUtente);
        if(u==null){  //username già utilizzato
            System.out.println("registrazione non riuscita");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else{
            System.out.println("registrazione riuscita per "+nuovoUtente.getNome());
            return new ResponseEntity<>(u, HttpStatus.CREATED);
        }
    }


    //TODO
    //mi serve per gestire aggiuni/rimuovi preferiti quindi dovrebbe essere PUT
    @PostMapping(value = "/{id}")
    public ResponseEntity<Utente> updatePreferiti(@PathVariable("id") String id, @RequestBody ProdottoInMagazzino p){
        System.out.println("utente " + id + " ha aggiunto il prodotto: " + p.getNome());
        Utente u = us.update(id, p);
        if(u==null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        System.out.println("PUT--->" + u);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    }
