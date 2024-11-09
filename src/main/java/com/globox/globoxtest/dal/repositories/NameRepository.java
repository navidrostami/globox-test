package com.globox.globoxtest.dal.repositories;

import com.globox.globoxtest.dal.entities.Name;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NameRepository extends JpaRepository<Name, String> {
    List<Name> findByPrimaryName(String primaryName);
}

