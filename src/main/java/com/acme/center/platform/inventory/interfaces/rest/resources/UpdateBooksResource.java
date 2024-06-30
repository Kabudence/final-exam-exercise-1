package com.acme.center.platform.inventory.interfaces.rest.resources;

import com.acme.center.platform.inventory.domain.model.valueobjects.BookStatus;
import com.acme.center.platform.inventory.domain.model.valueobjects.GenreTypes;

import java.util.Date;

public record UpdateBooksResource(Long bookId,
                                  String isbn,
                                  String title,
                                  String author,
                                  Date publishedDate,
                                  String status,
                                  String genreType) {
}
