package uk.co.bbc.mybbc.exceptions;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;
import uk.co.bbc.mybbc.entities.RestException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public RestException methodNotAllowed(HttpRequestMethodNotSupportedException exception) {
        log.info ("Invalid method: " + exception.getMessage ());
        return new RestException (HttpStatus.METHOD_NOT_ALLOWED, exception);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public RestException noHandlerFoundException(NoHandlerFoundException exception) {
        log.info ("No handler: " + exception.getMessage ());
        return new RestException (HttpStatus.NOT_FOUND, exception);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public RestException handleException(MethodArgumentNotValidException ex) {
        final List<String> errors = new ArrayList<String> ();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }

        log.info ("Invalid params: " + ex.getBindingResult().getAllErrors ());
        return new RestException(HttpStatus.NOT_ACCEPTABLE, "Invalid or Missing Parameter(s).", errors);
    }

    @ExceptionHandler(UserAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public RestException handleNotFoundException(UserAlreadyExists exception) {
        log.info ("User already exists: " + exception.getMessage ());
        return new RestException (HttpStatus.CONFLICT, exception.getMessage());
    }

    @ExceptionHandler(InvalidInputException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public RestException invalidInputException(InvalidInputException exception) {
        log.info ("Invalid inputs: " + exception.getMessage ());
        return new RestException (HttpStatus.NOT_ACCEPTABLE, exception.getMessage ());
    }

    @ExceptionHandler(InvalidNotificationInputException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public RestException invalidNotificationInputException(InvalidNotificationInputException exception) {
        log.info ("Invalid notification input: " + exception.getMessage ());
        return new RestException(HttpStatus.NOT_ACCEPTABLE, "Invalid or Missing Parameter(s).", exception.getErrors ());
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<?> invalidNotificationInputException(HttpClientErrorException ex) {
        RestException restException = new RestException(HttpStatus.BAD_REQUEST, ex.getMessage ());
        return new ResponseEntity<Object>(restException, HttpStatus.BAD_REQUEST);
    }
}