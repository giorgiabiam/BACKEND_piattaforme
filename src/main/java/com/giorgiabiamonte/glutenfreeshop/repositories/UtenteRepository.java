package com.giorgiabiamonte.glutenfreeshop.repositories;

import com.giorgiabiamonte.glutenfreeshop.models.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer> {
    Utente findByID(Integer id);
    Utente findByUsername(String username);
    boolean existsByUsername(String username);
}
