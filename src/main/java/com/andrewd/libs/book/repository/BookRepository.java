package com.andrewd.libs.book.repository;

import java.util.Optional;

import com.andrewd.libs.book.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

    Optional<Book> findByISBNOrISBN13(String ISBN, String ISBN13);
}
