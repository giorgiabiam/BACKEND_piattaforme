package com.giorgiabiamonte.glutenfreeshop.repositories;

import com.giorgiabiamonte.glutenfreeshop.entities.Acquisto;
import com.giorgiabiamonte.glutenfreeshop.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AcquistoRepository extends JpaRepository<Acquisto, Integer> {
    Acquisto findAcquistoByID(Integer ID);
    List<Acquisto> findByAcquirente(Utente utente);
}
