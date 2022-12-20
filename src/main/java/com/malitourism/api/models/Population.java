package com.malitourism.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="population")
@Data

public class Population {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long Id ;
    private int chiffre;
    private int annee;
    private String langue;

    @ManyToOne
    private Region region;
}
