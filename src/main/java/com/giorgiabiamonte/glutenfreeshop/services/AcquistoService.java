package com.giorgiabiamonte.glutenfreeshop.services;

import com.giorgiabiamonte.glutenfreeshop.entities.Acquisto;
import com.giorgiabiamonte.glutenfreeshop.entities.ProdottoAcquistato;
import com.giorgiabiamonte.glutenfreeshop.entities.ProdottoInMagazzino;
import com.giorgiabiamonte.glutenfreeshop.entities.Utente;
import com.giorgiabiamonte.glutenfreeshop.exception.QuantitaNonDisponibile;
import com.giorgiabiamonte.glutenfreeshop.repositories.AcquistoRepository;
import com.giorgiabiamonte.glutenfreeshop.repositories.ProdottoAcquistatoRepo;
import com.giorgiabiamonte.glutenfreeshop.repositories.ProdottoRepository;
import com.giorgiabiamonte.glutenfreeshop.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
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
    @Autowired
    private ProdottoRepository pr;
    @Autowired
    private ProdottoAcquistatoRepo par;

    @Transactional
    public Acquisto getAcquistoByID(Integer ID){
        return ar.findAcquistoByID(ID);
    }

    @Transactional(readOnly = true)
    public List<Acquisto> acquistiUtente(int idUtente) {
        return ar.findByAcquirente(ur.findById(idUtente).get());
    }

    @Transactional(readOnly = false)
    public Acquisto newAcquisto(Acquisto a) throws QuantitaNonDisponibile {
        Utente acquirente=ur.getOne(a.getAcquirente().getID());
        a.setAcquirente(acquirente);

        //devo controllare che in magazzino ci sia quantita sufficiente per ogni prodotto
        for(ProdottoAcquistato p : a.getListaProdotti()){
            ProdottoInMagazzino prodottoReale=pr.getOne(p.getProdottoReale().getCodice());
            p.setProdottoReale(prodottoReale);
            if( (p.getProdottoReale().getQta() - p.getQtaAcquistata())< 0)
                throw new QuantitaNonDisponibile();
        }

        //dopo il controllo sulla quantita posso rendere persistente la lista prodotti
        List<ProdottoAcquistato> listaProdottiAcquistati=new LinkedList<>();
        double totale=0;
        for(ProdottoAcquistato p: a.getListaProdotti()){
            listaProdottiAcquistati.add(par.save(p));
            totale+=p.getProdottoReale().getPrezzo() * p.getQtaAcquistata();

            //devo decremetare la quantità disponibile in magazzino
            int nuovaQta=p.getProdottoReale().getQta()-p.getQtaAcquistata();
            p.getProdottoReale().setQta(nuovaQta);
        }
        a.setTot(totale);
        a.setDataAcquisto(String.valueOf(System.currentTimeMillis()));
        a.setListaProdotti(listaProdottiAcquistati);
        Acquisto nuovoAcquisto=ar.save(a);
        entityManager.refresh(nuovoAcquisto); //sicura?
        return nuovoAcquisto;
    }


}
