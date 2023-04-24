package com.giorgiabiamonte.glutenfreeshop.repositories;

import com.giorgiabiamonte.glutenfreeshop.models.entities.Acquisto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcquistoRepository extends JpaRepository<Acquisto, Integer> {
    Acquisto findAcquistoByID(Integer ID);
//    List<Acquisto> findByAcquirente(Utente utente);
}
