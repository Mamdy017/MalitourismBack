package com.malitourism.api.Services;

import com.malitourism.api.models.Commentaire;

import java.util.List;

public interface CommentaireService {

    Commentaire creer(Commentaire commentaire);

    List<Object> afficherParId(int idregion, int iduser);

}
