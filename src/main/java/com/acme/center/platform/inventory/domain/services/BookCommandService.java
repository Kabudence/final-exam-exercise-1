package com.acme.center.platform.inventory.domain.services;

import com.acme.center.platform.inventory.domain.model.aggregates.Books;
import com.acme.center.platform.inventory.domain.model.commands.CreateBooksCommand;
import com.acme.center.platform.inventory.domain.model.commands.UpdateBooksCommand;

import java.awt.print.Book;
import java.util.Optional;

public interface BookCommandService {

    Optional<Books> handle(CreateBooksCommand command);
    Optional<Books> handle(UpdateBooksCommand command);


}
