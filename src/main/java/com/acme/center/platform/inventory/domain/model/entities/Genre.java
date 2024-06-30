package com.acme.center.platform.inventory.domain.model.entities;

import com.acme.center.platform.inventory.domain.model.valueobjects.GenreTypes;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class Genre {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(length = 40, nullable = false, unique = true)
    private GenreTypes name;

    public Genre() {
    }
    public Genre(GenreTypes genreTypes){
        this.name = genreTypes;
    }
}
