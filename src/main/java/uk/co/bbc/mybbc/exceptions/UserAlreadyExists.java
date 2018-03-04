package uk.co.bbc.mybbc.exceptions;


public class UserAlreadyExists extends RuntimeException {

    public UserAlreadyExists(String msg) {
        super(msg);
    }
}


