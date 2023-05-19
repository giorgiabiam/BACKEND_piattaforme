package com.giorgiabiamonte.glutenfreeshop.services;

import com.giorgiabiamonte.glutenfreeshop.models.entities.ProdottoAcquistato;
import com.giorgiabiamonte.glutenfreeshop.models.entities.ProdottoInMagazzino;
import com.giorgiabiamonte.glutenfreeshop.repositories.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdottoService {

    @Autowired
    private ProdottoRepository pr;

    @Transactional
    public List<ProdottoInMagazzino> getAll(){
        return pr.findAll();
    }

    @Transactional
    public ProdottoInMagazzino get(Integer codice) {
        if(!pr.existsByCodice(codice)){
            System.out.println("il prodotto con codice "+codice+"non esiste");
            return null;
        }
        else{
            return pr.findByCodice(codice);
        }
    }

}
