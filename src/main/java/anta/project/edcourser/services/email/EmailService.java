package anta.project.edcourser.services.email;

import java.util.List;

public interface EmailService {
    void sendEmailToAddressHtmlMessage(List<String> emails, String subject, String message);
    void sendEmailOnLogin(String email, String message);
    void sendEmailOnRegistration(String email, String message);
    void sendEmailOnChangePassword(String email, String message);
    void sendEmailOnResetPassword(String email, String message);
}
