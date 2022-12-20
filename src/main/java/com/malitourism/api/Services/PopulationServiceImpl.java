package com.malitourism.api.Services;

import com.malitourism.api.models.Population;
import com.malitourism.api.repository.PopulationRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@AllArgsConstructor

public class PopulationServiceImpl implements PopulationService {

    @Autowired
    private final PopulationRepository populationRepository;

    @Override
    public Population creer(Population population) {
        // TODO Auto-generated method stub
        return populationRepository.save(population);
    }
    @Override
    public List<Population> lire() {
        // TODO Auto-generated method stub
        return populationRepository.findAll();
    }

    @Override
    public Population modifier(Long Id, Population Population) {
        return populationRepository.findById(Id)
                .map(P->{
                    P.setChiffre(Population.getChiffre());
                    P.setAnnee(Population.getAnnee());
                    P.setLangue(Population.getLangue());
                    return populationRepository.save(P);
                }).orElseThrow(()->new RuntimeException("Population non trouvée"));
    }

    @Override
    public String supprimer(Long Id) {
        populationRepository.deleteById(Id);
        return "Population supprimée";
    }

}
