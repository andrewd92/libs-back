package com.andrewd.libs.book.exception;

public class BookAlreadyExists extends RuntimeException {
    public BookAlreadyExists() {
        super("Book already exists!");
    }
}
