package com.acme.center.platform.inventory.application.internal.queryservices;

import com.acme.center.platform.inventory.domain.model.aggregates.Books;
import com.acme.center.platform.inventory.domain.model.queries.GetAllBooksQuery;
import com.acme.center.platform.inventory.domain.services.BookQueryService;
import com.acme.center.platform.inventory.infrastructure.jpa.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;


@Service
public class BooksQueryServiceImpl  implements BookQueryService {

    private final BookRepository bookRepository;

    public BooksQueryServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    /**
     * Query handler to get all books
     * @param query containing book details
     *  @return List<Books>
     * */
    @Override
    public List<Books> handle(GetAllBooksQuery query) {
        return bookRepository.findAll();
    }
}
