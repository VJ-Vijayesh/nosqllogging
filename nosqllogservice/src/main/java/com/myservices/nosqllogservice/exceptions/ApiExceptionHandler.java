package com.myservices.nosqllogservice.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;

//controller advice for global exception handling
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    //When no handler for exception is found. property added for throwing exception if no handler found
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new ApiExceptionResponse(ex.getLocalizedMessage(),ex),HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new ValidationExceptionResponse("Validaiton Error", ex.getMessage(),HttpStatus.NOT_ACCEPTABLE),HttpStatus.NOT_ACCEPTABLE);
    }

    //When ApiException is thrown, this handler will execute. Controllers get method throws apiexception
    @ExceptionHandler({ApiException.class})
    public ResponseEntity<Object> handleApiItemNotFoundException(RuntimeException ex){
        return new ResponseEntity<>(new ApiExceptionResponse(ex.getMessage(),ex),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<String> handleValidationException(RuntimeException ex){

        return new ResponseEntity<>(ex.getLocalizedMessage(),HttpStatus.NOT_ACCEPTABLE);
    }
}
