package com.malitourism.api.Services;

import com.malitourism.api.models.Region;
import com.malitourism.api.repository.RegionRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Data
@Service
@AllArgsConstructor
public class RegionServiceImpl implements RegionService{


    private final RegionRepository regionRepository;

    @Override
    public Region creer(Region region) {

        return regionRepository.save(region);
    }

    @Override
    public List<Region> lire() {
        return regionRepository.findAll();
    }

    @Override
    public List<Object> afficherParId(int paysId) {
        return regionRepository.afficherParId(paysId);
    }

    @Override
    public Region modifier(Long Id, Region region) {
        // TODO Auto-generated method stub
        return regionRepository.findById(Id)
                .map(R->{
                    R.setCode_region(region.getCode_region());
                    R.setNom(region.getNom());
                    R.setActivite(region.getActivite());
                    R.setSuperficie(region.getSuperficie());
                    return regionRepository.save(R);
                }).orElseThrow(()->new RuntimeException("Region non trouvée"));
    }

    @Override
    public String supprimer(Long Id) {
        // TODO Auto-generated method stub
        regionRepository.deleteById(Id);
        return "Region supprimée";
    }

    @Override
    public Region regionParId(Long id) {
        return regionRepository.findById(id).get();
    }

    public List<Object[]> getRegionsP() {
        // TODO Auto-generated method stub
        return regionRepository.getRegionsP() ;
    }

    @Override
    public void uploaderImage(MultipartFile file ) throws IOException {
        System.out.println("----------------------------------------------");
//        String img3 = StringUtils.cleanPath(file.getOriginalFilename());
//        String uploRegion ="C:\\Users\\mccamara\\Desktop\\MaliTourist\\ApiRegion\\src\\main\\resources\\image";
        file.transferTo(new File("C:\\Users\\mccamara\\Desktop\\MaliTourist\\Nouveau dossier\\Scucom\\src\\main\\resources\\image"+file.getOriginalFilename()));
//        image.saveimgR(uploRegion, img3,  file);
    }


}
