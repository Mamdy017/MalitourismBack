package com.malitourism.api.repository;

import com.malitourism.api.models.Population;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PopulationRepository extends JpaRepository<Population,Long> {

    @Query(value = "SELECT * FROM `population` WHERE region.pays_id=:regionId;",
            nativeQuery = true)
    List<Object> afficherParId(int regionId);


}
