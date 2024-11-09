package com.globox.globoxtest.dal.entities;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Cacheable
@Table(name = "title_ratings")
@Data
public class TitleRating {

    @Id
    private String tconst;
    private Double averageRating;
    private Integer numVotes;

}
