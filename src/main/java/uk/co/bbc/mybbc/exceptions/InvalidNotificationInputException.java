package uk.co.bbc.mybbc.exceptions;

import java.util.List;

public class InvalidNotificationInputException extends RuntimeException {


    private List<String> errors;

    public InvalidNotificationInputException(String msg, List<String> errors) {
        super(msg);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

}
