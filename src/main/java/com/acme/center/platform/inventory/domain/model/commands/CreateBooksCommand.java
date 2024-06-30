package com.acme.center.platform.inventory.domain.model.commands;

import com.acme.center.platform.inventory.domain.model.valueobjects.BookStatus;
import com.acme.center.platform.inventory.domain.model.valueobjects.GenreTypes;

import java.util.Date;


public record CreateBooksCommand(String isbn,
                                 String title,
                                 String author,
                                 Date publishedDate,
                                 BookStatus status,
                                 GenreTypes genreType) {
}
