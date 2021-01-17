package anta.project.edcourser.utils;

import org.apache.commons.validator.routines.EmailValidator;

public class Validator {

    public boolean isValidEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    public boolean isValidPassword(String password) {
        return password != null && password.length() > 4;
    }
}
