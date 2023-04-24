package com.giorgiabiamonte.glutenfreeshop.config;

import com.giorgiabiamonte.glutenfreeshop.models.entities.Utente;
import com.giorgiabiamonte.glutenfreeshop.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitDB implements CommandLineRunner {
    @Autowired
    UtenteRepository ur;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("ciao");
        Utente u=new Utente();
        u.setNome("paola");
        u.setCognome("guarasci");
        u.setConvenzionato(true);
        u.setPassword("ppppp");
        u.setUsername("paola_guarasci");
        u.setIndirizzo("indirizzo");

        ur.save(u);
    }
}
