package com.malitourism.api.repository;

import com.malitourism.api.models.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Long> {
    // void save(Population population);
    // void save(Pays pays);
    @Query(value="SELECT nom, code_region, activite,superficie,langue,chiffre,annee from region,population where region.id=population.region_id",nativeQuery=true)
    //@Query(value="SELECT * from Region,Population where Region=Population.region")
    List<Object[]> getRegionsP();

    @Query(value="SELECT region.nom, code_region, activite,superficie,langue,chiffre,annee,pays.nom from region,population,pays \n" +
            "where region.id=population.region_id AND region.pays_id=pays.id AND nom=?",nativeQuery=true)
    List<Object[]> getRegionsAvecPays(String nom);


}
