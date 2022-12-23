package com.malitourism.api.Services;

import com.malitourism.api.models.Commentaire;
import com.malitourism.api.repository.CommentaireRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@AllArgsConstructor
public class CommentaireServiceImp implements CommentaireService {
    @Autowired
    CommentaireRepository commentaireRepository;

    @Override
    public Commentaire creer(Commentaire commentaire) {
        return this.commentaireRepository.save(commentaire);
    }

    @Override
    public List<Object> afficherParId(int idregion, int iduser) {
        return commentaireRepository.afficherParId(idregion,iduser);
    }
}
