package com.acme.center.platform.inventory.interfaces.rest.transform;

import com.acme.center.platform.inventory.domain.model.commands.UpdateBooksCommand;
import com.acme.center.platform.inventory.domain.model.valueobjects.BookStatus;
import com.acme.center.platform.inventory.domain.model.valueobjects.GenreTypes;
import com.acme.center.platform.inventory.interfaces.rest.resources.UpdateBooksResource;

public class UpdateCommandFromResource {
    public static UpdateBooksCommand toCommandFromResource(UpdateBooksResource resource){
        BookStatus bookStatus = BookStatus.valueOf(resource.status().toUpperCase());
        GenreTypes genreType = GenreTypes.valueOf(resource.genreType().toUpperCase());
        return new UpdateBooksCommand(resource.bookId(),
                resource.isbn(),
                resource.title(),
                resource.author(),
                resource.publishedDate(),
                bookStatus, genreType);
    }
}
