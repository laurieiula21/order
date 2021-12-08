package com.switchfully.order.domain.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String message) {

        super(message);
    }
}
