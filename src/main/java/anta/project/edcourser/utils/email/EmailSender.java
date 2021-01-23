package anta.project.edcourser.utils.email;

import anta.project.edcourser.services.mail.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmailSender implements EmailService {

  @Value("${spring.mail.username}")
  private String emailOfSender;

  private static final String LOGIN_SUBJECT = "EdCourser | Login notification";
  private static final String REGISTRATION_CONFIRMATION = "EdCourser | Registration Confirmation";
  private static final String CHANGE_PASS_CONFIRM = "EdCourser | Change password";
  private static final String RESET_PASS = "EdCourser | Reset password";

  private final ExecutorService executorService = Executors.newCachedThreadPool();
  private final JavaMailSender mailSender;

  @SneakyThrows
  private void sendHtml(String toEmail, String subject, String emailMessage){
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
    mimeMessage.setContent(emailMessage, "text/html");
    helper.setText(emailMessage, true);
    helper.setTo(toEmail);
    helper.setSubject(subject);
    helper.setFrom(emailOfSender);
    mailSender.send(mimeMessage);
  }

  @Override
  public void sendEmailToAddressHtmlMessage(List<String> emails, String subject, String message) {
    executorService.submit(() -> emails.forEach(email -> sendHtml(email, subject, message)));
  }

  @Override
  public void sendEmailOnLogin(String email, String message) {
    sendEmailToAddressHtmlMessage(Collections.singletonList(email), LOGIN_SUBJECT, message);
  }

  @Override
  public void sendEmailOnRegistration(String email, String message) {
    final String htmlMessage = HTMLemailPattern.getConfirmRegisterMessage(email, message);
    sendEmailToAddressHtmlMessage(Collections.singletonList(email), REGISTRATION_CONFIRMATION, htmlMessage);
  }

  @Override
  public void sendEmailOnChangePassword(String email, String message) {
    final String htmlMessage = HTMLemailPattern.getRestorePasswordMessage(email,message);
    sendEmailToAddressHtmlMessage(Collections.singletonList(email), CHANGE_PASS_CONFIRM, htmlMessage);
  }

  @Override
  public void sendEmailOnResetPassword(String email, String message) {
    sendEmailToAddressHtmlMessage(Collections.singletonList(email), RESET_PASS, message);
  }

}