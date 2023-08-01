package com.lincon.bookstoremanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookExceptionHandler {
    @ExceptionHandler(BookException.class)
    public ResponseEntity<BookErrorResponse> handleException(BookException ex) {
        BookErrorResponse errorResponse = new BookErrorResponse("Erro personalizado", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
