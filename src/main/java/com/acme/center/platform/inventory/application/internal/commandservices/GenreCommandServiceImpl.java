package com.acme.center.platform.inventory.application.internal.commandservices;

import com.acme.center.platform.inventory.domain.model.commands.SendGenreCommand;
import com.acme.center.platform.inventory.domain.model.entities.Genre;
import com.acme.center.platform.inventory.domain.model.valueobjects.GenreTypes;
import com.acme.center.platform.inventory.domain.services.GenreCommandService;
import com.acme.center.platform.inventory.infrastructure.jpa.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class GenreCommandServiceImpl implements GenreCommandService {

    private final GenreRepository genreRepository;

    public GenreCommandServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
    /**
     * Command handler to send genre
     * @param command containing genre details
     **/
    @Override
    public void handle(SendGenreCommand command) {
        Arrays.stream(GenreTypes.values()).forEach(name -> {
            if (!genreRepository.existsByName(name)) {
                genreRepository.save(new Genre(name));
            }
        });

    }
}
