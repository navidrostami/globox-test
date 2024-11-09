package com.globox.globoxtest.dal.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "title_principals")
@Data
public class TitlePrincipal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tconst;
    private Integer ordering;
    private String nconst;
    private String category;
    @Column(columnDefinition = "TEXT")
    private String job;
    @Column(columnDefinition = "TEXT")
    private String characters;

}

