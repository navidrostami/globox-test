package com.globox.globoxtest.dal.repositories;

import com.globox.globoxtest.dal.entities.Title;
import com.globox.globoxtest.dtos.RatedTitleDTO;
import jakarta.persistence.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TitleRepository extends JpaRepository<Title, String> {

    @Query(value = "SELECT DISTINCT t.* FROM titles t " +
                   "JOIN title_crew tc ON t.tconst = tc.nconst " +
                   "JOIN LATERAL regexp_split_to_table(tc.directors, '\\s*,\\s*') AS dir(nconst) ON TRUE " +
                   "JOIN LATERAL regexp_split_to_table(tc.writers, '\\s*,\\s*') AS wrt(nconst) ON dir.nconst = wrt.nconst " +
                   "JOIN names n ON n.nconst = dir.nconst " +
                   "WHERE n.death_year IS NULL ",
                    nativeQuery = true)
    List<Title> findTitlesByDirectorAndWriterAliveNative();

    @Query(value = "SELECT DISTINCT t.* FROM titles t " +
                   "JOIN title_principals tp1 ON t.tconst = tp1.tconst " +
                   "JOIN title_principals tp2 ON t.tconst = tp2.tconst " +
                   "JOIN names n1 ON tp1.nconst = n1.nconst " +
                   "JOIN names n2 ON tp2.nconst = n2.nconst " +
                   "WHERE n1.primary_name = :actor1 AND n2.primary_name = :actor2",
                   nativeQuery = true)
    List<Title> findTitlesByActorsNative(String actor1, String actor2);

    @Query(value = "SELECT start_year, primary_title, average_rating, num_votes FROM ( " +
                   "SELECT t.start_year, t.tconst, t.primary_title, tr.average_rating, tr.num_votes, " +
                   "ROW_NUMBER() OVER (PARTITION BY t.start_year ORDER BY tr.num_votes DESC, tr.average_rating DESC) as rank " +
                   "FROM titles t " +
                   "JOIN title_ratings tr ON t.tconst = tr.tconst " +
                   "WHERE t.genres ILIKE CONCAT('%' , :genre , '%')) ranked_titles " +
                   "WHERE rank = 1",
                   nativeQuery = true)
    List<Object[]> findBestTitlesByGenreNative(String genre);
}

