package com.malitourism.api.Services;

import com.malitourism.api.models.Population;

import java.util.List;

public interface PopulationService {

    Population creer(Population population);
    List<Population> lire();
    Population modifier(Long Id, Population population);
    String supprimer(Long Id);
    List<Object> afficherParId(int regionId);
}
