package com.andrewd.libs.user.exception;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists() {
        super("User already exists!");
    }
}
