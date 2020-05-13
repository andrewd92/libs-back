package com.andrewd.libs.wishlist.service;

import java.util.List;

import com.andrewd.libs.book.domain.Book;
import com.andrewd.libs.book.service.BookService;
import com.andrewd.libs.wishlist.domain.WishList;
import com.andrewd.libs.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WishListService {

    private final BookService bookService;

    private final WishListRepository repository;

    public WishList addToWishList(long userId, long bookId) {
        Book book = bookService.getBook(bookId);

        return repository.save(
                WishList.builder()
                        .userId(userId)
                        .bookId(book.getId())
                        .ISBN(book.getISBN())
                        .ISBN13(book.getISBN13())
                        .title(book.getTitle())
                        .authors(book.getAuthors())
                        .description(book.getDescription())
                        .imageUrl(book.getImageUrl())
                        .build()
        );
    }

    public List<WishList> getFor(long userId) {
        return repository.findAllByUserId(userId);
    }

}
