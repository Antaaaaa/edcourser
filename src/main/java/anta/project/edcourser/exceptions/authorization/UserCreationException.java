package anta.project.edcourser.exceptions.authorization;

import anta.project.edcourser.exceptions.CustomRuntimeException;

public class UserCreationException extends CustomRuntimeException {
    public UserCreationException(String message) {super(message);}
}
