package com.malitourism.api.repository;

import com.malitourism.api.models.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaysRepository extends JpaRepository<Pays,Long> {

    @Query(value = "SELECT SUM(pays.sperficie) FROM pays",nativeQuery = true)
    Object getTotalSuperficie();

    @Query(value = "SELECT SUM(population.chiffre) FROM population",nativeQuery = true)
    Object getTotalsPop();
}
