package com.acme.center.platform.inventory.domain.services;

import com.acme.center.platform.inventory.domain.model.aggregates.Books;
import com.acme.center.platform.inventory.domain.model.queries.GetAllBooksQuery;

import java.util.List;

public interface BookQueryService {

    List<Books> handle(GetAllBooksQuery query);
}
