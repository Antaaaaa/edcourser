package anta.project.edcourser.exceptions.authorization;

import anta.project.edcourser.exceptions.CustomRuntimeException;

public class UserTokenNotFoundException extends CustomRuntimeException {
    public UserTokenNotFoundException(String message) {super(message);}
}
