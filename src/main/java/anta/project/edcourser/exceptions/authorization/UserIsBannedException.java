package anta.project.edcourser.exceptions.authorization;

public class UserIsBannedException extends RuntimeException {
    public UserIsBannedException(String message) {
        super(message);
    }
}
