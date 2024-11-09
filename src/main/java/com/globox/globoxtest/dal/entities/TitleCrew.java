package com.globox.globoxtest.dal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "title_crew")
@Data
public class TitleCrew {
    @Id
    private String nconst;
    @Column(columnDefinition = "TEXT")
    private String directors;
    @Column(columnDefinition = "TEXT")
    private String writers;     // Comma-separated tconsts

}

