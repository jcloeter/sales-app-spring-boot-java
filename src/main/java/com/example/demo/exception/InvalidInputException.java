package com.example.demo.exception;

public class InvalidInputException extends RuntimeException{
    public InvalidInputException(String s){
        super(s);
    }
}
