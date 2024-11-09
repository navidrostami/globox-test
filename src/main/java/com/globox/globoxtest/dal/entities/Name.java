package com.globox.globoxtest.dal.entities;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Cacheable
@Table(name = "names")
@Data
public class Name {
    @Id
    private String nconst;
    private String primaryName;
    private Integer birthYear;
    private Integer deathYear;
    private String primaryProfession;  // Comma-separated professions
    private String knownForTitles;     // Comma-separated tconsts

}

