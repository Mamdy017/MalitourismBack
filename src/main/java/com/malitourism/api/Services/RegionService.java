package com.malitourism.api.Services;


import com.malitourism.api.models.Pays;
import com.malitourism.api.models.Region;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RegionService {


    Region creer(Region region);

    List<Region> lire();
    List<Object> afficherParId(int paysId);
    Region modifier(Long Id, Region region);

    String supprimer(Long Id);

    Region regionParId(Long id);

    List<Object[]>getRegionsP();

    void uploaderImage(MultipartFile f1) throws IOException;


}
