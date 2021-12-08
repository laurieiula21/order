package com.switchfully.order.api.controllers;


import com.switchfully.order.domain.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler({
            NullPointerException.class,
            InvalidCustomerException.class,
            InvalidItemException.class,
            InvalidFieldValueException.class,
            NoSuchElementException.class,
            UsernameAlreadyExistsException.class
    })
    protected void creatingElementError(RuntimeException ex ,
                                     HttpServletResponse response) throws IOException {
        logger.error(ex.getMessage());
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(InvalidAdminInformationException.class)
    protected void invalidUserError(InvalidAdminInformationException ex, HttpServletResponse response) throws IOException{
        logger.error(ex.getMessage());
        response.sendError(HttpStatus.FORBIDDEN.value(), ex.getMessage());
    }
}
