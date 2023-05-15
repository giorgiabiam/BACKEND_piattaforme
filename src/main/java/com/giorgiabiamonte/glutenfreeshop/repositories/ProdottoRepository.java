package com.giorgiabiamonte.glutenfreeshop.repositories;

import com.giorgiabiamonte.glutenfreeshop.models.entities.ProdottoAcquistato;
import com.giorgiabiamonte.glutenfreeshop.models.entities.ProdottoInMagazzino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoRepository extends JpaRepository<ProdottoInMagazzino, Integer> {
    ProdottoAcquistato findByCodice(Integer codiceProdotto);
}
