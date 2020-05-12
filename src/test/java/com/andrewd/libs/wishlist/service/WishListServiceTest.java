package com.andrewd.libs.wishlist.service;

import com.andrewd.libs.book.domain.Book;
import com.andrewd.libs.book.service.BookService;
import com.andrewd.libs.wishlist.domain.WishList;
import com.andrewd.libs.wishlist.repository.WishListRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WishListServiceTest {

    @InjectMocks
    private WishListService service;

    @Mock
    private WishListRepository repository;

    @Mock
    private BookService bookService;

    @Test
    public void addToWishListShouldCreateNewWishList() {
        long userId = 1;
        long bookId = 1;

        Book book = Book.builder()
                .id(bookId)
                .ISBN("1")
                .ISBN13("1")
                .title("title")
                .authors("authors")
                .description("description")
                .imageUrl("1.png")
                .build();

        when(bookService.getBook(bookId)).thenReturn(book);

        WishList expectedWish = WishList.builder()
                .userId(userId)
                .bookId(book.getId())
                .ISBN(book.getISBN())
                .ISBN13(book.getISBN13())
                .title(book.getTitle())
                .authors(book.getAuthors())
                .description(book.getDescription())
                .imageUrl(book.getImageUrl())
                .build();

        when(repository.save(expectedWish)).thenReturn(expectedWish);

        assertEquals(expectedWish, service.addToWishList(userId, bookId));
    }

}
