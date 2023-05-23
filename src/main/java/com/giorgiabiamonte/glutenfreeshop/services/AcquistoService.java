package com.giorgiabiamonte.glutenfreeshop.services;

import com.giorgiabiamonte.glutenfreeshop.models.Carrello;
import com.giorgiabiamonte.glutenfreeshop.models.entities.Acquisto;
import com.giorgiabiamonte.glutenfreeshop.models.entities.ProdottoAcquistato;
import com.giorgiabiamonte.glutenfreeshop.models.entities.ProdottoInMagazzino;
import com.giorgiabiamonte.glutenfreeshop.models.entities.Utente;
import com.giorgiabiamonte.glutenfreeshop.repositories.AcquistoRepository;
import com.giorgiabiamonte.glutenfreeshop.repositories.ProdottoAcquistatoRepo;
import com.giorgiabiamonte.glutenfreeshop.repositories.ProdottoRepository;
import com.giorgiabiamonte.glutenfreeshop.repositories.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.giorgiabiamonte.glutenfreeshop.utils.exception.QuantitaNonDisponibile;


import jakarta.persistence.EntityManager;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AcquistoService {

    @Autowired
    private EntityManager entityManager;

    private final AcquistoRepository acquisto_repo;
    private final UtenteRepository utente_repo;
    private final ProdottoAcquistatoRepo acquistatoRepo;
    private final ProdottoRepository prodottoRepo;

    @Transactional
    public List<Acquisto> getAcquistiUtente(int idUtente) {
        return acquisto_repo.findByAcquirente(utente_repo.findById(idUtente).get());
    }

    @Transactional (readOnly = false)
    public Acquisto newAcquisto(Carrello carrello, Integer IDutente) throws QuantitaNonDisponibile {
        List<ProdottoAcquistato> listaProd = new LinkedList<>();
        for(int i : carrello.getMap().keySet()){
            //TODO controllo quantità
            ProdottoInMagazzino preal = prodottoRepo.findByCodice(i);
            int qta_acquistata = carrello.getMap().get(i);
            if (  (preal.getQta()-qta_acquistata) < 0 ){
                throw new QuantitaNonDisponibile();
            }
            else{
                ProdottoAcquistato pa = new ProdottoAcquistato();
                pa.setProdottoReale(preal);
                pa.setQtaAcquistata(qta_acquistata);
                System.out.println("nuovo prodotto acq--- "+pa);
                acquistatoRepo.save(pa);
                listaProd.add(pa);
            }
        }
        //TODO scalare il totale dal saldo
        Acquisto acquisto = new Acquisto();
        acquisto.setDataAcquisto(new Date().toString());
        Utente u = utente_repo.findByID(IDutente);
        acquisto.setAcquirente(u);
        acquisto.setTot(carrello.getTotale());
        acquisto.setListaProdotti(listaProd);
//        entityManager.refresh(acquisto);
        acquisto_repo.save(acquisto);
        return  acquisto;
    }
}
