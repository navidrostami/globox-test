package com.globox.globoxtest.services;


import com.globox.globoxtest.dal.repositories.TitleRepository;
import com.globox.globoxtest.dtos.RatedTitleDTO;
import com.globox.globoxtest.dtos.TitleDTO;
import com.globox.globoxtest.mappers.TitleMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class TitleService {


    private static final Logger logger = LoggerFactory.getLogger(TitleService.class);

    private final TitleMapper titleMapper = Mappers.getMapper(TitleMapper.class);

    @Autowired
    private TitleRepository titleRepository;


    //  1
    public List<TitleDTO> getTitlesWithSameDirectorAndWriter() {
        try {
            return titleMapper.toTitlesDTO(titleRepository.findTitlesByDirectorAndWriterAliveNative());
        } catch (Exception e) {
            if (e.getCause() != null) {
                logger.error("Exception cause: ", e.getCause());
            } else {
                logger.error("An error occurred while fetching TitlesWithSameDirectorAndWriter: ", e);
            }
            return Collections.emptyList();
        }
    }
    //  2
    public List<TitleDTO> getCommonTitlesBetweenActors(String actor1, String actor2) {
        try {
          return titleMapper.toTitlesDTO(titleRepository.findTitlesByActorsNative(actor1,actor2));
        } catch (Exception e) {
            if (e.getCause() != null) {
                logger.error("Exception cause: ", e.getCause());
            } else {
                logger.error("An error occurred while fetching CommonTitlesBetweenActors: ", e);
            }
            return Collections.emptyList();
        }
    }

    //  3
    public List<RatedTitleDTO> getBestTitlesByGenre(String genre) {
        try {
            List<RatedTitleDTO> result= new ArrayList<>();
            for (Object[] obj: titleRepository.findBestTitlesByGenreNative(genre)) {
                Integer startYear = (Integer) obj[0];
                String primaryTitle = (String) obj[1];
                Double averageRating = (Double) obj[2];
                Integer numVotes = (Integer) obj[3];
                result.add(new RatedTitleDTO(startYear,primaryTitle,averageRating,numVotes));
            }
             return result;
        } catch (Exception e) {
            if (e.getCause() != null) {
                logger.error("Exception cause: ", e.getCause());
            } else {
                logger.error("An error occurred while fetching BestTitlesByGenre: ", e);
            }
            return Collections.emptyList();
        }
    }

}

