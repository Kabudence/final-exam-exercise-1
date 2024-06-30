package com.acme.center.platform.inventory.application.internal.commandservices;

import com.acme.center.platform.inventory.domain.model.aggregates.Books;
import com.acme.center.platform.inventory.domain.model.commands.CreateBooksCommand;
import com.acme.center.platform.inventory.domain.model.commands.UpdateBooksCommand;
import com.acme.center.platform.inventory.domain.model.valueobjects.BookStatus;
import com.acme.center.platform.inventory.domain.services.BookCommandService;
import com.acme.center.platform.inventory.infrastructure.jpa.repositories.BookRepository;
import com.acme.center.platform.inventory.infrastructure.jpa.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.Date;
import java.util.Optional;

@Service
public class BooksCommandServiceImpl implements BookCommandService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    public BooksCommandServiceImpl(BookRepository bookRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }

/**
 * Command handler to create book
 * @param command containing book details
 * @return Books
   */
    @Override
    public Optional<Books> handle(CreateBooksCommand command) {
        validation(command.isbn(), command.publishedDate(), command.status());
        var genre = genreRepository.findByName(command.genreType());
        var bookEntity=new Books(command,genre.get());
        bookRepository.save(bookEntity);
        return Optional.of(bookEntity);

    }
    /**
     * Command handler to update book
     * @param command containing book details
     * @return Books
     * */
    @Override
    public Optional<Books> handle(UpdateBooksCommand command) {
        validation(command.isbn(), command.publishedDate(), command.status());
        var result = bookRepository.findById(command.bookId());
        if (result.isEmpty()) throw new IllegalArgumentException("Book does not exist");
        var bookToUpdate = result.get();
        try{
            var updatedBook=bookRepository.save(bookToUpdate.updateInformation(command));
            return Optional.of(updatedBook);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Error while updating book: " + e.getMessage());
        }
    }
    /**
     * Validation method to check if the book already exists, if the publishDate is before the currentDate and if the status already exists
     * @param isbn
     * @param date
     * @param status
      **/
    private void validation(String isbn, Date date, BookStatus status) {
        Date currentDate = new Date();
        if (bookRepository.existsByIsbn(isbn))
            throw new IllegalArgumentException("Books with same isbn already exists");
        if(!(date.before(currentDate)|| date.equals(currentDate)))
          throw new IllegalArgumentException("The publishDate cannot be before or equal them currentDate");

    }

}
