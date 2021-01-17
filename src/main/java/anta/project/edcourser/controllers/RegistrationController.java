package anta.project.edcourser.controllers;

import anta.project.edcourser.config.rest.model.MvcResponse;
import anta.project.edcourser.config.rest.model.MvcResponseError;
import anta.project.edcourser.config.rest.resources.Statuses;
import anta.project.edcourser.dto.authorization.UserRegistrationDTO;
import anta.project.edcourser.exceptions.NotValidRegistrationDataException;
import anta.project.edcourser.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@Log4j2
public class RegistrationController {

    private final UserService userService;

    @PostMapping(value = "/registration")
    public MvcResponse registration(@RequestBody UserRegistrationDTO data,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        try {
            userService.register(data);
        } catch (NotValidRegistrationDataException exception) {
            return new MvcResponseError(Statuses.RegistrationFailed, exception.getMessage());
        } catch (Exception ex) {
            return new MvcResponseError(400, "Error registration");
        }
        return new MvcResponse(Statuses.Ok);
    }
}
