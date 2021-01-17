package anta.project.edcourser.utils;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;

public class Validator {

    public static boolean isValidEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.length() > 4;
    }
}
