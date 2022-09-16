package com.myservices.nosqllogservice.exceptions;

import org.springframework.http.HttpStatus;

public class ValidationExceptionResponse {
    private String custommessage;
    private String exceptionMessage;
    private HttpStatus statuscode;

    public ValidationExceptionResponse(String custommessage, String exceptionMessage, HttpStatus statuscode) {
        this.custommessage = custommessage;
        this.statuscode = statuscode;
        this.exceptionMessage=exceptionMessage;
    }
}
