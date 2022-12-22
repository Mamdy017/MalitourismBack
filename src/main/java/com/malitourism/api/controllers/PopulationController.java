package com.malitourism.api.controllers;


import com.malitourism.api.Services.PopulationService;
import com.malitourism.api.Services.RegionService;
import com.malitourism.api.models.Population;
import com.malitourism.api.models.Region;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;


import java.util.List;@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/population")

@AllArgsConstructor

@Api(value = "apiregion", description = "Une API de GESTION des REGIONS pour faciliter a l'agence de touristes")
public class PopulationController {

    private final PopulationService populationService;
    private  final RegionService regionService;

    @ApiOperation(value = "Ajouter une population")
    @PostMapping("/creer/{idRegion}")
    public Population Creer(@Param("chiffre") String chiffre, @Param("langue") String langue, @Param("annee") String annee, @PathVariable("idRegion") Long idRegion ) {
       Population population = new Population();
       population.setChiffre(chiffre);
       population.setLangue(langue);
       population.setAnnee(annee);
       Region region = regionService.regionParId(idRegion);
       population.setRegion(region);
        return populationService.creer(population);
    }

    @ApiOperation(value = "La liste des populations")
    @GetMapping("/afficher")
    public List<Population> afficher(){
        return populationService.lire();

    }

    @ApiOperation(value = "Modifier une population")
    @PutMapping("/Modifier/{Id}")
    public Population Modifier(@PathVariable Long Id, Population population) {
        return populationService.modifier( Id, population);
    }

    @ApiOperation(value = "Supprimer une population")
    @DeleteMapping("/supprimer/{Id}")
    public String Supprimer(@PathVariable Long Id) {

        return populationService.supprimer(Id);
    }


}
