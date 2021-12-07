package com.switchfully.order.domain.exceptions;

public class InvalidAdminInformationException extends RuntimeException {
    public InvalidAdminInformationException(String message) {
        super(message);
    }
}
