package ru.tsystems.tsproject.sbb.services.exception;

/**
 * Created by apple on 23.10.14.
 */
public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
