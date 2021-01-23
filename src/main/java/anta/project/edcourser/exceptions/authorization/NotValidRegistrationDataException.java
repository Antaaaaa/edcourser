package anta.project.edcourser.exceptions.authorization;

import anta.project.edcourser.exceptions.CustomRuntimeException;

public class NotValidRegistrationDataException extends CustomRuntimeException {
    public NotValidRegistrationDataException(String message) {super(message);}
}
