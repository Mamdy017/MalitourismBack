package com.malitourism.api.repository;

import com.malitourism.api.models.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentaireRepository extends JpaRepository<Commentaire ,Long> {

    @Query(value = "SELECT commentaire.commentaire,users.username,region.nom,commentaire.id FROM `commentaire`, users,region WHERE commentaire.region_id=:idregion AND commentaire.user_id=:iduser ORDER BY commentaire.id DESC;",
            nativeQuery = true)
    List<Object> afficherParId(int idregion, int iduser);

}
