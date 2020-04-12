package com.andrewd.libs.user.exception;

public class UserNotFound extends RuntimeException {
    public UserNotFound() {
        super("User not found!");
    }
}
