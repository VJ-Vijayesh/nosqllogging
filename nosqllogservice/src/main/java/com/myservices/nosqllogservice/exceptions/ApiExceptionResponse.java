package com.myservices.nosqllogservice.exceptions;

//Response for custom exceptions
public class ApiExceptionResponse {
    private String message;
    private Throwable e;


    public ApiExceptionResponse(String message, Throwable e) {
        this.message = message;
        this.e = e;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getE() {
        return e;
    }


}
