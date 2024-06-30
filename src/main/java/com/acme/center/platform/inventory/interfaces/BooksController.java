package com.acme.center.platform.inventory.interfaces;

import com.acme.center.platform.inventory.domain.model.queries.GetAllBooksQuery;
import com.acme.center.platform.inventory.domain.services.BookCommandService;
import com.acme.center.platform.inventory.domain.services.BookQueryService;
import com.acme.center.platform.inventory.interfaces.rest.resources.BooksResource;
import com.acme.center.platform.inventory.interfaces.rest.resources.CreateBooksResource;
import com.acme.center.platform.inventory.interfaces.rest.resources.UpdateBooksResource;
import com.acme.center.platform.inventory.interfaces.rest.transform.CreateBooksResourceFromEntity;
import com.acme.center.platform.inventory.interfaces.rest.transform.CreateCommandFromResource;
import com.acme.center.platform.inventory.interfaces.rest.transform.UpdateCommandFromResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v3/books", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Books", description = "Books Controller")
public class BooksController {

    private final BookCommandService bookCommandService;
    private final BookQueryService bookQueryService;

    public BooksController(BookCommandService bookCommandService, BookQueryService bookQueryService) {
        this.bookCommandService = bookCommandService;
        this.bookQueryService = bookQueryService;
    }

   @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LIBRARIAN')")
    @PostMapping
    public ResponseEntity<BooksResource> createBooks(@RequestBody CreateBooksResource resource) {
        var createCommand= CreateCommandFromResource.toCommandFromResource(resource);
        var bookEntity=bookCommandService.handle(createCommand);
        if(bookEntity.isEmpty()) return ResponseEntity.badRequest().build();
        var bookResource= CreateBooksResourceFromEntity.toResourceFromEntity(bookEntity.get());
        return new ResponseEntity<>(bookResource, HttpStatus.CREATED);

    }
    @PreAuthorize("hasRole('ROLE_ADMIN' || 'ROLE_LIBRARIAN')")
    @PutMapping("/{bookId}")
    public ResponseEntity<BooksResource> updateBooks(@PathVariable Long bookId, @RequestBody UpdateBooksResource resource) {
        var updateCommand= UpdateCommandFromResource.toCommandFromResource(resource);
        var bookEntity=bookCommandService.handle(updateCommand);
        if(bookEntity.isEmpty()) return ResponseEntity.badRequest().build();
        var bookResource= CreateBooksResourceFromEntity.toResourceFromEntity(bookEntity.get());
        return new ResponseEntity<>(bookResource, HttpStatus.CREATED);


    }
    @PreAuthorize("permitAll()")
    @GetMapping
    public ResponseEntity<List<BooksResource>> getAllBooks() {
        var getAllBooksQuery = new GetAllBooksQuery();
        var books = bookQueryService.handle(getAllBooksQuery);
        var bookResources = books.stream()
                .map(CreateBooksResourceFromEntity::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(bookResources);
    }

}