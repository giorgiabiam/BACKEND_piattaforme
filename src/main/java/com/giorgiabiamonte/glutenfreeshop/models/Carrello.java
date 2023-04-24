package com.giorgiabiamonte.glutenfreeshop.models;

import com.giorgiabiamonte.glutenfreeshop.models.entities.ProdottoAcquistato;
import com.giorgiabiamonte.glutenfreeshop.models.entities.Utente;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Component
@SessionScope
public class Carrello {
    private Utente utente;
    private List<ProdottoAcquistato> listaProdotti;

    //TODO
}
