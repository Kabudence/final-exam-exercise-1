package com.acme.center.platform.inventory.infrastructure.jpa.repositories;

import com.acme.center.platform.inventory.domain.model.entities.Genre;
import com.acme.center.platform.inventory.domain.model.valueobjects.GenreTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> {

    boolean existsByName(GenreTypes name);
    Optional<Genre>findByName(GenreTypes name);
}
