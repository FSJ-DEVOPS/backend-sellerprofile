package com.shopverse.sellerprofile.controller;

import com.shopverse.sellerprofile.model.ErrorDetails;
import com.shopverse.sellerprofile.utils.BookNotFoundException;
import com.shopverse.sellerprofile.utils.SellerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends Exception {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        ErrorDetails error = new ErrorDetails(new Date(), request.getDescription(false), ex.getMessage());
        return new ResponseEntity<ErrorDetails>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<?> handleBookNotFoundException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Book not Found" , ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SellerNotFoundException.class)
    public ResponseEntity<?> handleFlavorNotFoundException(Exception ex, WebRequest request) {
        ErrorDetails error = new ErrorDetails(new Date(), "Seller Not found", ex.getMessage());
        return new ResponseEntity<ErrorDetails>(error, HttpStatus.NOT_FOUND);
    }
}
