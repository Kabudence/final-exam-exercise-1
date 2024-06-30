package com.acme.center.platform.inventory.domain.model.aggregates;

import com.acme.center.platform.inventory.domain.model.commands.CreateBooksCommand;
import com.acme.center.platform.inventory.domain.model.commands.UpdateBooksCommand;
import com.acme.center.platform.inventory.domain.model.entities.Genre;
import com.acme.center.platform.inventory.domain.model.valueobjects.BookStatus;
import com.acme.center.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.Date;

@Entity
public class Books extends AuditableAbstractAggregateRoot<Books> {

    @Getter
    @Size(max = 13)
    @NotBlank
    private String isbn;

    @Getter
    @Size(max = 13)
    @NotBlank
    private String title;

    @Getter
    @Size(max = 50)
    @NotBlank
    private String author;

    @Getter
    @NotBlank
    private Date publishedDate;

    @Getter
    @Enumerated(EnumType.STRING)
    @NotBlank
    private BookStatus status;

    @JoinColumn(name = "genre_id")
    @ManyToOne
    private Genre genre;


    public Books (CreateBooksCommand command){
        this.isbn = command.isbn();
        this.title = command.title();
        this.author = command.author();
        this.publishedDate = command.publishedDate();
        this.status = command.status();
        this.genre = new Genre(command.genreType());

    }

    public Books updateInformation(UpdateBooksCommand command) {
        this.isbn = command.isbn();
        this.title = command.title();
        this.author = command.author();
        this.publishedDate = command.publishedDate();
        this.status = command.status();
        this.genre = new Genre(command.genreType());
        return this;
    }


    public Books() {

    }


    public String getGenreName() {
        return genre.getName().toString();
    }
}
