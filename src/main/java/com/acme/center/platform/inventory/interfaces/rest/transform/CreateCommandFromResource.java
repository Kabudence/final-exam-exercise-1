package com.acme.center.platform.inventory.interfaces.rest.transform;

import com.acme.center.platform.inventory.domain.model.commands.CreateBooksCommand;
import com.acme.center.platform.inventory.domain.model.valueobjects.BookStatus;
import com.acme.center.platform.inventory.domain.model.valueobjects.GenreTypes;
import com.acme.center.platform.inventory.interfaces.rest.resources.CreateBooksResource;

public class CreateCommandFromResource {

    public static CreateBooksCommand toCommandFromResource(CreateBooksResource resource){
        BookStatus bookStatus = BookStatus.valueOf(resource.status().toUpperCase());
        GenreTypes genreType = GenreTypes.valueOf(resource.genreType().toUpperCase());
        return new CreateBooksCommand(resource.isbn(), resource.title(), resource.author(), resource.publishedDate(), bookStatus, genreType);
    }
}
