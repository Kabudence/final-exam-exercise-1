package com.acme.center.platform.inventory.interfaces.rest.transform;

import com.acme.center.platform.inventory.domain.model.aggregates.Books;
import com.acme.center.platform.inventory.interfaces.rest.resources.BooksResource;
import com.acme.center.platform.inventory.interfaces.rest.resources.CreateBooksResource;

public class CreateBooksResourceFromEntity {
    public static BooksResource toResourceFromEntity(Books entity){
        return new BooksResource(entity.getId(),
                entity.getIsbn(),
                entity.getTitle(),
                entity.getAuthor(),
                entity.getPublishedDate(),
                entity.getStatus().name(),
                entity.getGenreName());
    }


}
