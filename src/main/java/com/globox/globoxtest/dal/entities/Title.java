package com.globox.globoxtest.dal.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Cacheable
@Table(name = "titles")
@Data
public class Title {

    @Id
    private String tconst;
    private String titleType;
    @Column(columnDefinition = "TEXT")
    private String primaryTitle;
    @Column(columnDefinition = "TEXT")
    private String originalTitle;
    private Integer isAdult;
    private Integer startYear;
    private Integer endYear;
    private Integer runtimeMinutes;
    @Column(columnDefinition = "TEXT")
    private String genres; // Comma-separated genres

}

