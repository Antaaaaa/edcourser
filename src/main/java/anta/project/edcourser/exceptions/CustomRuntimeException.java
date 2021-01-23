package anta.project.edcourser.exceptions;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomRuntimeException extends RuntimeException {
    public CustomRuntimeException(String message) {
        super(message, null, false ,false);
        log.error(String.format("Error: %s with message: %s", getClass().getSimpleName(), message));
    }
}
