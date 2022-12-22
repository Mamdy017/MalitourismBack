package com.malitourism.api.repository;

import com.malitourism.api.models.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentaireRepository extends JpaRepository<Commentaire ,Long> {
}
