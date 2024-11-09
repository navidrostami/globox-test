package com.globox.globoxtest.dal.repositories;

import com.globox.globoxtest.dal.entities.TitlePrincipal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TitlePrincipalRepository extends JpaRepository<TitlePrincipal, Long> {
    List<TitlePrincipal> findByTconst(String tconst);
    List<TitlePrincipal> findByNconst(String nconst);
}
