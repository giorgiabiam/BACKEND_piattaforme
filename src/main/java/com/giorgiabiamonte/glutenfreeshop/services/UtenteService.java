package com.giorgiabiamonte.glutenfreeshop.services;

import com.giorgiabiamonte.glutenfreeshop.models.entities.ProdottoInMagazzino;
import com.giorgiabiamonte.glutenfreeshop.models.entities.Utente;
import com.giorgiabiamonte.glutenfreeshop.repositories.ProdottoRepository;
import com.giorgiabiamonte.glutenfreeshop.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository ur;
    @Autowired
    private ProdottoRepository prepo;

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
    public Utente login(String username, String password){
        if( !ur.existsByUsernameAndPassword(username, password) ){//username o password errati
            return null;
        }
        else{
            Utente u = ur.findByUsername(username);
            return u;
        }
    }

    @Transactional
    public Utente signin(Utente nuovoUtente){
        if( !ur.existsByUsername(nuovoUtente.getUsername()) ){
            Utente u = Utente.builder().username(nuovoUtente.getUsername()).password(nuovoUtente.getPassword())
                        .nome(nuovoUtente.getNome()).cognome(nuovoUtente.getCognome())
                        .convenzionato(nuovoUtente.isConvenzionato()).build();

            if(u.isConvenzionato()){
                u.setSaldo(90);
            }

            ur.save(u);
            return u;
        }
        else{ //username già utilizzato
            return null;
        }
    }


    @Transactional
    public Utente update(String id, ProdottoInMagazzino p) {
        if( !ur.existsById(Integer.parseInt(id)) || !prepo.existsById(p.getCodice()) ){
            System.out.println("errore update");
            return null;
        }
        else{
            Utente u = ur.findByID(Integer.parseInt(id));
            u.getPreferiti().add(p);
            System.out.println("UPDATE" + u);
            return ur.save(u);
        }
    }

}
