package com.andrewd.libs.book.api.controller;


import javax.validation.Valid;

import com.andrewd.libs.book.api.request.NewBook;
import com.andrewd.libs.book.domain.Book;
import com.andrewd.libs.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book/v1")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/")
    public Book addBook(@Valid @RequestBody NewBook request) {
        return bookService.addBook(request);
    }
}