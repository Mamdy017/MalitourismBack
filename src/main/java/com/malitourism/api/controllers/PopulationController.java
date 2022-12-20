package com.malitourism.api.controllers;

import com.malitourism.api.Services.PopulationService;
import com.malitourism.api.models.Population;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequestMapping("/population")

@AllArgsConstructor

@Api(value = "apiregion", description = "Une API de GESTION des REGIONS pour faciliter a l'agence de touristes")
public class PopulationController {

    private final com.malitourism.api.Services.PopulationService PopulationService;

    @ApiOperation(value = "Ajouter une population")
    @PostMapping("/creer")
    public Population Creer(@RequestBody Population population) {
        return PopulationService.creer(population);
    }

    @ApiOperation(value = "La liste des populations")
    @GetMapping("/afficher")
    public List<Population> afficher(){
        return PopulationService.lire();

    }

    @ApiOperation(value = "Modifier une population")
    @PutMapping("/Modifier/{Id}")
    public Population Modifier(@PathVariable Long Id, Population population) {
        return PopulationService.modifier( Id, population);
    }

    @ApiOperation(value = "Supprimer une population")
    @DeleteMapping("/supprimer/{Id}")
    public String Supprimer(@PathVariable Long Id) {

        return PopulationService.supprimer(Id);
    }


}
