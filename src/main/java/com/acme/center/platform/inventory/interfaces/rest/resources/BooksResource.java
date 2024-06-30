package com.acme.center.platform.inventory.interfaces.rest.resources;

import java.util.Date;

public record BooksResource(Long bookId,
                            String isbn,
                            String title,
                            String author,
                            Date publishedDate,
                            String status,
                            String genreType) {
}
