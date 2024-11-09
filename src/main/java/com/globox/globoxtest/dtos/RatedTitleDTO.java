package com.globox.globoxtest.dtos;

import lombok.Value;

@Value
public class RatedTitleDTO {

    Integer startYear;
    String primaryTitle;
    Double averageRating;
    Integer numVotes;
}

