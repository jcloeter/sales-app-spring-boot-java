package com.example.demo.exception;

import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class.getName());

    class ExceptionResponse{

        private final String message;

        public ExceptionResponse(String message){
            this.message = message;
        }

        public String getMessage(){ return this.message; }
    }
    
    @ExceptionHandler(ProductNotFoundException.class) //404
    public ResponseEntity<ExceptionResponse> handleProductNotFoundException(Exception ex){
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class) // 404
    public ResponseEntity<ExceptionResponse> handleOrderNotFoundException(Exception ex){
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidInputException.class) // 400
    public ResponseEntity<ExceptionResponse> handleInvalidInputException(Exception ex){
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
