package com.giorgiabiamonte.glutenfreeshop.controller;

import com.giorgiabiamonte.glutenfreeshop.models.JwtResponse;
import com.giorgiabiamonte.glutenfreeshop.models.LoginRequest;
import com.giorgiabiamonte.glutenfreeshop.models.entities.ProdottoInMagazzino;
import com.giorgiabiamonte.glutenfreeshop.models.entities.Utente;
import com.giorgiabiamonte.glutenfreeshop.services.AuthService;
import com.giorgiabiamonte.glutenfreeshop.services.UtenteService;
import com.giorgiabiamonte.glutenfreeshop.utils.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/utenti")
@RequiredArgsConstructor
@CrossOrigin(origins="*") //TODO   "http://localhost:4200"
public class UtenteController {

    @Autowired
    private UtenteService us;
    private final AuthService authService;

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


    @PostMapping(value = "/auth/login", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest req) {
//      TODO Encoder per la password va usato qui o nel front ?
//      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//      String encodedPassword = passwordEncoder.encode(req.getPassword());

        System.out.println("sono nel controller login");
        String jwt = authService.authenticate(req);
        Utente utente = us.findByUsername(req.getUsername());
        JwtResponse res = new JwtResponse(jwt, utente);
        return new ResponseEntity<>(res, HttpStatus.OK);

//        Utente u = us.login(req.getUsername(), req.getPassword()); //encodedPassword
//        if (u == null) {
//            System.out.println("login non riuscito");
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        } else {
//            System.out.println("login riuscito per " + u.getNome());
//            return new ResponseEntity<>(u, HttpStatus.OK);
//        }
    }

    @PostMapping(value = "/auth/register")
    public ResponseEntity<Utente> registrazione(@Valid @RequestBody Utente nuovoUtente){
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
