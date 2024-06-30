package com.acme.center.platform.inventory.infrastructure.jpa.repositories;

import com.acme.center.platform.inventory.domain.model.aggregates.Books;
import com.acme.center.platform.inventory.domain.model.valueobjects.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Books, Long> {

    boolean existsByIsbn(String isbn);
}
