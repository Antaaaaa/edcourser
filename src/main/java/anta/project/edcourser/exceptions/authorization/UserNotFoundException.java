package anta.project.edcourser.exceptions.authorization;

import anta.project.edcourser.exceptions.CustomRuntimeException;

public class UserNotFoundException extends CustomRuntimeException {
    public UserNotFoundException(String message) {super(message);}
}
