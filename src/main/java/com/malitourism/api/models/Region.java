package com.malitourism.api.models;


import lombok.Data;

import javax.persistence.*;
@Entity
@Table(name="region")
@Data
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )

    private Long Id ;
    @Column(unique = true)
    private String code_region;
    private String nom;
    private String activite;
    private String superficie;
    private String img1;
    private String img2;
    private String img3;
    private String description;

    @ManyToOne
    Pays pays;
}
