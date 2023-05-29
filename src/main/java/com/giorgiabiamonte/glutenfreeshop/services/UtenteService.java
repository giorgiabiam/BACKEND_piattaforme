package com.giorgiabiamonte.glutenfreeshop.services;

import com.giorgiabiamonte.glutenfreeshop.models.entities.Utente;
import com.giorgiabiamonte.glutenfreeshop.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository ur;

    @Transactional
    public List<Utente> getAll() {
        var res = ur.findAll();
        System.out.println(res);
        return res;
    }

    @Transactional
    public Utente findByID(int id){
        return ur.findByID(id);
    }

    @Transactional
    public Utente findByUsername(String username){ return ur.findByUsername(username); }

    @Transactional
    public Utente signin(Utente nuovoUtente){
        System.out.println("--------"+ nuovoUtente);
        if( !ur.existsByUsername(nuovoUtente.getUsername()) ){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            Utente u = Utente.builder().username(nuovoUtente.getUsername()).password(encoder.encode(nuovoUtente.getPassword()) )
                    .ruolo(nuovoUtente.getRuolo()).nome(nuovoUtente.getNome()).cognome(nuovoUtente.getCognome()).indirizzo(nuovoUtente.getIndirizzo()).build();
            ur.save(u);
            return u;
        }
        else{
            System.out.println("username già utilizzato");
            return null;
        }
    }

}
