package net.axel.ebanking.exception.domain;

public class UsernameAlreadyExistsException extends RuntimeException {

    public UsernameAlreadyExistsException(String username) {
        super("The username '" + username + "' already exists.");
    }
}
