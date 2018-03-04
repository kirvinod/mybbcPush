package uk.co.bbc.mybbc.entities;

import org.springframework.http.HttpStatus;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class RestException {

    private HttpStatus status;

    private String message;

    private List<String> errors = new ArrayList<> ();

    private Instant timestamp;

    public RestException(HttpStatus status, Exception exception) {
        this.status = status;
        this.message = exception.getMessage();
        this.timestamp = Instant.now();
    }

    public RestException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = Instant.now();
    }

    public RestException(HttpStatus status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.timestamp = Instant.now();
    }


    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
