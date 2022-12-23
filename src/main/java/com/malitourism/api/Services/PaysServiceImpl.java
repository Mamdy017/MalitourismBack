package com.malitourism.api.Services;

import com.malitourism.api.models.Pays;
import com.malitourism.api.repository.PaysRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class PaysServiceImpl implements PaysService {
    @Autowired
    private final
    PaysRepository paysRepository;
    @Override
    public
    Pays creer(Pays pays) {
        return this.paysRepository.save(pays);
    }

    @Override
    public List<Pays> lire() {
        return this.paysRepository.findAll();
    }

    @Override
    public Pays modifier(Long Id, Pays pays) {
        return this.paysRepository.findById(Id).map(p->{
                    p.setNom(pays.getNom());
                    return paysRepository.save(p);
                }
        ).orElseThrow(()-> new  RuntimeException("Pays non trouvé"));
    }
    @Override
    public String supprimer(Long Id) {
        this.paysRepository.deleteById(Id);
        return "Pays" +Id+ "supprimer avec succès";
    }

    @Override
    public Pays paysParId(Long id) {
        return paysRepository.findById(id).get();
    }

    @Override
    public Object getAllSuperficie() {
        return paysRepository.getTotalSuperficie();
    }

    @Override
    public Object getTotalsPop() {
        return paysRepository.getTotalsPop();
    }
}