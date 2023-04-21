package com.giorgiabiamonte.glutenfreeshop.services;


import com.giorgiabiamonte.glutenfreeshop.entities.Utente;
import com.giorgiabiamonte.glutenfreeshop.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository ur;

    @Transactional
    public Utente newUser(Utente u) {
        return ur.save(u);
    }

    @Transactional
    public List<Utente> getAll() {
        var res = ur.findAll();
        System.out.println(res);
        return res;
    }

    @Transactional
    public Utente findUtenteByID(int id){
        return ur.findByID(id);
    }

    @Transactional
    public boolean esisteUtente(String username, String password) {
        return ur.existsByUsernameAndPassword(username, password);
    }

    @Transactional(readOnly = true)
    public Utente findUtenteByUsername(String username) {
        return ur.findByUsername(username);
    }


}
