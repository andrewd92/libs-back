package com.andrewd.libs.book.service;

import java.util.List;
import java.util.Optional;

import com.andrewd.libs.book.api.request.NewBook;
import com.andrewd.libs.book.domain.Book;
import com.andrewd.libs.book.exception.BookAlreadyExists;
import com.andrewd.libs.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book addBook(NewBook request) {

        Optional<Book> bookCandidate = bookRepository.findByISBNOrISBN13(request.getISBN(), request.getISBN13());

        if (bookCandidate.isPresent()) {
            throw new BookAlreadyExists();
        }

        return bookRepository.save(
                Book.builder()
                    .title(request.getTitle())
                    .imageUrl(request.getImageUrl())
                    .ISBN(request.getISBN())
                    .ISBN13(request.getISBN13())
                    .publisher(request.getPublisher())
                    .authors(request.getAuthors())
                    .publishDate(request.getPublishDate())
                    .pages(request.getPages())
                    .description(request.getDescription())
                .build()
        );
    }

    public Book getBook(long id) {
        return bookRepository.findById(id).get();
    }

    public List<Book> find(String query) {
        return bookRepository.findByTitleContainsOrISBNContainsOrISBN13Contains(query, query, query);
    }
}
