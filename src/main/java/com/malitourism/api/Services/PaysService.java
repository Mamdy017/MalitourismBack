package com.malitourism.api.Services;

import com.malitourism.api.models.Pays;

import java.util.List;
import java.util.Optional;

public interface PaysService {


    Pays creer(Pays pays);

    List<Pays> lire();

    Pays modifier(Long Id, Pays pays);

    String supprimer(Long Id);

    Pays paysParId(Long id);

}
