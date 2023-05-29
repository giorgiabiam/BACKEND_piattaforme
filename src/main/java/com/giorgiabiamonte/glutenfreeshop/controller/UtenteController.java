package com.giorgiabiamonte.glutenfreeshop.controller;

import com.giorgiabiamonte.glutenfreeshop.utils.jwt.JwtResponse;
import com.giorgiabiamonte.glutenfreeshop.services.AuthService;
import com.giorgiabiamonte.glutenfreeshop.models.LoginRequest;
import com.giorgiabiamonte.glutenfreeshop.models.entities.Utente;

import com.giorgiabiamonte.glutenfreeshop.services.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/utenti")
@RequiredArgsConstructor
@CrossOrigin(origins="*") //TODO   "http://localhost:4200"
public class UtenteController {

    @Autowired
    private UtenteService us;
    private final AuthService authService;

    @GetMapping("/{id}")
    public ResponseEntity<Utente> getUtenteByID(@PathVariable("id") Integer id){
        Utente utente = us.findByID(id);
        return new ResponseEntity<>(utente, HttpStatus.OK);
    }

    @PostMapping(value = "/auth/login", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest req) {
        String jwt = authService.authenticate(req);
        Utente utente = us.findByUsername(req.getUsername());
        JwtResponse res = new JwtResponse(jwt, utente.getID());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping(value = "/auth/registrazione")
    public ResponseEntity<Utente> registrazione(@RequestBody Utente nuovoUtente){
        Utente u = us.signin(nuovoUtente);
        System.out.println("NEL CONTROLLER dopo il save(u) " + nuovoUtente);
        if(u==null){
            System.out.println("registrazione non riuscita");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
//            String jwt = authService.registrazione(u);
//            System.out.println("JWT  "+jwt);
//            JwtResponse res = new JwtResponse(jwt, u.getID());
//
//            System.out.println("registrazione riuscita----"+ res);

            return new ResponseEntity<>(u, HttpStatus.CREATED);
        }
    }

}
