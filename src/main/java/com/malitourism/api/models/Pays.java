package com.malitourism.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="pays")
@Data
@NoArgsConstructor


public class Pays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long Id ;
    @Column(unique = true, length = 20)
    private String nom;
    @Column(unique = true, length = 20)
    private String capital;
    private String drapeau;
    private String sperficie;


    public Pays(long idPays) {
        this.Id = idPays;
    }
}
