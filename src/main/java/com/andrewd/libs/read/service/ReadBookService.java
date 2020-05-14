package com.andrewd.libs.read.service;

import java.util.List;

import com.andrewd.libs.book.domain.Book;
import com.andrewd.libs.book.service.BookService;
import com.andrewd.libs.profile.service.ProfileService;
import com.andrewd.libs.read.domain.ReadBook;
import com.andrewd.libs.read.repository.ReadBooksRepository;
import com.andrewd.libs.reading.service.ReadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadBookService {

    private final ReadBooksRepository readBooksRepository;

    private final BookService bookService;

    private final ProfileService profileService;

    private final ReadingService readingService;

    public ReadBook read(long userId, long bookId) {
        Book book = bookService.getBook(bookId);

        ReadBook response = readBooksRepository.save(
                ReadBook.builder()
                        .userId(userId)
                        .bookId(book.getId())
                        .title(book.getTitle())
                        .authors(book.getAuthors())
                        .description(book.getDescription())
                        .imageUrl(book.getImageUrl())
                .build()
        );

        profileService.readBookAdded(userId);

        readingService.newBookRead(userId, bookId);

        return response;
    }

    public List<ReadBook> getForUser(long userId) {
        return readBooksRepository.findAllByUserId(userId);
    }
}
