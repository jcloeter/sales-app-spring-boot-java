package com.example.demo.exception;

public class ProductNotFoundException extends RuntimeException{

    // Note: "Checked Exceptions" are exceptions that extend Exception
    // They require you to list them in the method definition
    // Joshua Bloch recommends you use checked exceptions when the exception is recoverable
        // One other article seems to suggest adding unchecked exceptions to Java was a bit of a mistake
        // because of the amount of boilerplace code it adds to method signatures 

    // An unchecked exception extends RunTimeException
    // They represent an exception that cannot be recovered from
    // Bloch says "When in doubt, throw an unchecked (unrecoverable) exception"
        // Benefit to this is that method definitions don't get cluttered

    public ProductNotFoundException(String message){
        super(message);
    }
}
