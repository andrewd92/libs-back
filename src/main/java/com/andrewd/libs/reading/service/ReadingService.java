package com.andrewd.libs.reading.service;

import java.util.List;
import java.util.Optional;

import com.andrewd.libs.book.domain.Book;
import com.andrewd.libs.book.service.BookService;
import com.andrewd.libs.profile.service.ProfileService;
import com.andrewd.libs.reading.domain.ReadingBook;
import com.andrewd.libs.reading.repository.ReadingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReadingService {

    private final ReadingRepository readingRepository;

    private final BookService bookService;

    private final ProfileService profileService;

    public List<ReadingBook> get(long userId) {
        return readingRepository.findAllByUserId(userId);
    }

    public ReadingBook add(long userId, long bookId) {
        Book book = bookService.getBook(bookId);

        ReadingBook readingBook = readingRepository.save(
                ReadingBook.builder()
                        .userId(userId)
                        .bookId(book.getId())
                        .title(book.getTitle())
                        .authors(book.getAuthors())
                        .description(book.getDescription())
                        .imageUrl(book.getImageUrl())
                        .build()
        );

        profileService.readingBookAdded(readingBook.getUserId());

        return readingBook;
    }

    public void newBookRead(long userId, long bookId) {
        Optional<ReadingBook> bookCandidate = readingRepository.findByUserIdAndBookId(userId, bookId);

        if (bookCandidate.isPresent()) {
            readingRepository.delete(bookCandidate.get());

            profileService.readingBookFinished(userId);
        }
    }
}
