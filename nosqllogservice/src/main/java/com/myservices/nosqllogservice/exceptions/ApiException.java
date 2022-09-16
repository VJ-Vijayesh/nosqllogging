package com.myservices.nosqllogservice.exceptions;

//class to handle Api exceptions with custom messages
public class ApiException extends RuntimeException{
    public ApiException(String message) {
        super(message);
    }

    public ApiException() {
    }
}
