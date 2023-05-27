package com.giorgiabiamonte.glutenfreeshop.services;

import com.giorgiabiamonte.glutenfreeshop.models.Carrello;
import com.giorgiabiamonte.glutenfreeshop.models.CarrelloItem;
import com.giorgiabiamonte.glutenfreeshop.models.entities.Acquisto;
import com.giorgiabiamonte.glutenfreeshop.models.entities.ProdottoAcquistato;
import com.giorgiabiamonte.glutenfreeshop.models.entities.ProdottoInMagazzino;
import com.giorgiabiamonte.glutenfreeshop.models.entities.Utente;
import com.giorgiabiamonte.glutenfreeshop.repositories.AcquistoRepository;
import com.giorgiabiamonte.glutenfreeshop.repositories.ProdottoAcquistatoRepo;
import com.giorgiabiamonte.glutenfreeshop.repositories.ProdottoRepository;
import com.giorgiabiamonte.glutenfreeshop.repositories.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.giorgiabiamonte.glutenfreeshop.utils.exception.QuantitaNonDisponibile;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AcquistoService {

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
        for(CarrelloItem item: carrello.getLista()){

            ProdottoInMagazzino preal = prodottoRepo.findByCodice(item.getProdotto().getCodice());
            int qta_acquistata = item.getQta_acquist();
            if (  (preal.getQta() - qta_acquistata) < 0 ){
                throw new QuantitaNonDisponibile();
            }
            else{
                int nuovaQta = preal.getQta() - item.getQta_acquist();
                preal.setQta(nuovaQta);
                //TODO aggiornare l'entità prodotto visto che ho modificato la quantità
                prodottoRepo.save(preal);

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
        acquisto_repo.save(acquisto);
        return  acquisto;
    }

    public Acquisto getAcquisto(int idAcq) {
        return acquisto_repo.findAcquistoByID(idAcq);
    }
    public List<Acquisto> getAll() {
        return acquisto_repo.findAll();
    }
}
