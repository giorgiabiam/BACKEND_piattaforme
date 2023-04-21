package com.giorgiabiamonte.glutenfreeshop.repositories;

import com.giorgiabiamonte.glutenfreeshop.entities.ProdottoInMagazzino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoRepository extends JpaRepository<ProdottoInMagazzino, Integer> {
}
