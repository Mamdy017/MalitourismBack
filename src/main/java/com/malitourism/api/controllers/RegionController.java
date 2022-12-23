package com.malitourism.api.controllers;

import com.malitourism.api.Services.PaysService;
import com.malitourism.api.Services.RegionService;
import com.malitourism.api.img.image;
import com.malitourism.api.models.Message;
import com.malitourism.api.models.Pays;
import com.malitourism.api.models.Region;
import com.malitourism.api.repository.PaysRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/region")
@Api(value = "apiregion", description = "Une API de GESTION des REGIONS pour faciliter a l'agence de touristes")
@AllArgsConstructor


public class RegionController {

    private final RegionService regionService;

    private final PaysRepository paysRepository;
    private final PaysService paysService;

    @ApiOperation(value = "Ajouter une region")
    @PostMapping("/ajout/{idPays}")
    public Object create(@Param("nom") String nom, @Param("code_region") String code_region,
                         @Param("activite") String activite, @Param("superficie") String superficie,
                         @Param("description") String description,
                         @Param("img1") MultipartFile img1, @Param("img2") MultipartFile img2, @Param("img3") MultipartFile img3,@PathVariable Long idPays) throws IOException {

        System.out.println("je suis l'erreur");
        Region region = new Region();
        region.setNom(nom);
        region.setCode_region(code_region);
        region.setActivite(activite);
        region.setSuperficie(superficie);
        region.setDescription(description);
        System.err.println(nom);
        System.err.println(code_region);
        System.err.println(activite);
        System.err.println(superficie);
        System.err.println(description);
        Pays pays = paysService.paysParId(idPays);
        region.setPays(pays);
        String image1 = StringUtils.cleanPath(img1.getOriginalFilename());
        String image2 = StringUtils.cleanPath(img2.getOriginalFilename());
        String image3 = StringUtils.cleanPath(img3.getOriginalFilename());
        region.setImg1(image1);
        region.setImg2(image2);
        region.setImg3(image3);
        String uploRegion = "C:\\Users\\Camara\\Desktop\\Nouveau dossier (2)\\MaliTourist\\src\\assets\\mesimages";
        image.saveimgR(uploRegion, image1, img1);
        image.saveimgR(uploRegion, image2, img2);
        image.saveimgR(uploRegion, image3, img3);
        return regionService.creer(region);

    }

    @GetMapping("/afficherParId/{paysId}")
    List<Object> afficherParId ( @PathVariable int paysId){
        return regionService.afficherParId(paysId);

    }


    @ApiOperation(value = "La liste des regions")
    @GetMapping("/afficher")
    public List<Region> Lire() {
        return regionService.lire();

    }

    @ApiOperation(value = "La liste des regions sans pays")
    @GetMapping("/sans")
    public List<Object[]> Afficher() {
        return regionService.getRegionsP();

    }

    @ApiOperation(value = "Modifier une region")
    @PutMapping("/modifier/{Id}")
    public Region Modifier(@PathVariable Long Id, Region Region) {
        return regionService.modifier(Id, Region);
    }

    @ApiOperation(value = "Supprimer une region")
    @DeleteMapping("/delete/{Id}")
    public String delete(@PathVariable Long Id) {
        return regionService.supprimer(Id);
    }

    @ApiOperation(value = "recuperer un pays par son id")
    @GetMapping("/{id}")
    Region regionParId(@PathVariable Long id) {
        return regionService.regionParId(id);
    }

}
