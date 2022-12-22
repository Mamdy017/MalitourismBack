package com.malitourism.api.controllers;

import com.malitourism.api.Services.CommentaireService;
import com.malitourism.api.Services.RegionService;
import com.malitourism.api.models.Commentaire;
import com.malitourism.api.models.Region;
import com.malitourism.api.models.User;
import com.malitourism.api.security.services.CrudService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/commentaire")
public class CommentaireController {


     @Autowired
    CommentaireService commentaireService;

     @Autowired
    RegionService regionService;

     @Autowired
    CrudService crudService;

    @ApiOperation(value = "Ajouter un pays")
    @PostMapping("/creer/{idregion}/{iduser}")

    public Object creer(@Param("commentaire") String commentaire, @PathVariable("idregion") Long idregion,@PathVariable("iduser") Long iduser
                        ) throws IOException {
        Commentaire com= new Commentaire();
        com.setCommentaire(commentaire);
        Region region = regionService.regionParId(idregion);
        com.setRegion(region);

        User user = crudService.userParId(iduser);
        com.setUser(user);

        return commentaireService.creer(com);
    }
}
