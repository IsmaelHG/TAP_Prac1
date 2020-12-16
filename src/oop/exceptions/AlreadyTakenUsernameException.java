package oop.exceptions;

public class AlreadyTakenUsernameException extends Exception {

    public AlreadyTakenUsernameException(String message) {
        super(message);
    }
}