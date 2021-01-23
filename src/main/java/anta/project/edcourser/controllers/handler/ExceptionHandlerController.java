package anta.project.edcourser.controllers.handler;

import anta.project.edcourser.config.rest.model.MvcResponseError;
import anta.project.edcourser.config.rest.resources.Statuses;
import anta.project.edcourser.exceptions.authorization.NotValidRegistrationDataException;
import anta.project.edcourser.exceptions.authorization.UserCreationException;
import anta.project.edcourser.exceptions.authorization.UserNotFoundException;
import anta.project.edcourser.exceptions.authorization.UserTokenNotFoundException;
import anta.project.edcourser.exceptions.data.ChangeEmailDataException;
import anta.project.edcourser.exceptions.data.ChangePaswordDataException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
@ResponseStatus(HttpStatus.OK)
public class ExceptionHandlerController {

    @ExceptionHandler(ChangeEmailDataException.class)
    public MvcResponseError handleChangeEmail(Exception ex){
        return new MvcResponseError(Statuses.ChangeMailFailed, ex.getMessage());
    }

    @ExceptionHandler(ChangePaswordDataException.class)
    public MvcResponseError handlePasswordException(Exception ex){
        return new MvcResponseError(Statuses.ChangePasswordFailed, ex.getMessage());
    }

    @ExceptionHandler(NotValidRegistrationDataException.class)
    public MvcResponseError handleRegistrationException(Exception ex){
        return new MvcResponseError(Statuses.RegistrationFailed, ex.getMessage());
    }

    @ExceptionHandler(UserCreationException.class)
    public MvcResponseError handleUserCreation(Exception ex){
        return new MvcResponseError(Statuses.UserCreationFailed, ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public MvcResponseError handleUserNotFound(Exception ex) {
        return new MvcResponseError(Statuses.UserNotExists, ex.getMessage());
    }

    @ExceptionHandler(UserTokenNotFoundException.class)
    public MvcResponseError handleUserToken(Exception ex){
        return new MvcResponseError(Statuses.InvalidToken, ex.getMessage());
    }

}