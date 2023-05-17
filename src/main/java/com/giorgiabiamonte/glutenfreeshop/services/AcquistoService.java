package com.giorgiabiamonte.glutenfreeshop.services;

import com.giorgiabiamonte.glutenfreeshop.models.Carrello;
import com.giorgiabiamonte.glutenfreeshop.models.entities.Acquisto;
import com.giorgiabiamonte.glutenfreeshop.models.entities.ProdottoAcquistato;
import com.giorgiabiamonte.glutenfreeshop.repositories.AcquistoRepository;
import com.giorgiabiamonte.glutenfreeshop.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.giorgiabiamonte.glutenfreeshop.utils.exception.QuantitaNonDisponibile;


import jakarta.persistence.EntityManager;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class AcquistoService {

    @Autowired
    private AcquistoRepository ar;
    @Autowired
    private UtenteRepository ur;
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public List<Acquisto> getAcquistiUtente(int idUtente) {
        return ar.findByAcquirente(ur.findById(idUtente).get());
    }

    @Transactional (readOnly = false)
    public Acquisto newAcquisto(Carrello carrello, Integer IDutente) throws QuantitaNonDisponibile {
        //controllo quantità disponibile in magazzino
        for(ProdottoAcquistato p : carrello.getListaProdotti()){
            if( (p.getProdottoReale().getQta() - p.getQtaAcquistata()) < 0){
                throw new QuantitaNonDisponibile();
            }
        }
        //se va a buon fine il controllo rendo persistente la lista prodotti acquistati
        List<ProdottoAcquistato> listaProdottiAcquistati = new LinkedList<>();
        double totale = 0d;
        for(ProdottoAcquistato p : carrello.getListaProdotti()){
            listaProdottiAcquistati.add(p);
            totale += p.getQtaAcquistata() * p.getProdottoReale().getPrezzo();

            //decremento quantità in magazzino
            int nuovaQta = p.getProdottoReale().getQta() - p.getQtaAcquistata();
            p.getProdottoReale().setQta(nuovaQta); //ATTENZIONE NON SO SE FUNZIONA FACENDO COSI'
        }

        Acquisto nuovoAcquisto = new Acquisto();
        nuovoAcquisto.setAcquirente(ur.findByID(IDutente));
        nuovoAcquisto.setListaProdotti(listaProdottiAcquistati);
        nuovoAcquisto.setTot(totale);
        nuovoAcquisto.setDataAcquisto(new Date().toString());
        ar.save(nuovoAcquisto);
        entityManager.refresh(nuovoAcquisto);
        return nuovoAcquisto;
    }
}
