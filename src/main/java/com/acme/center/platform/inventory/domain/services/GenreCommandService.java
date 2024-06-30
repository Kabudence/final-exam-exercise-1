package com.acme.center.platform.inventory.domain.services;

import com.acme.center.platform.inventory.domain.model.commands.SendGenreCommand;

public interface GenreCommandService {

     void handle(SendGenreCommand command);
}
