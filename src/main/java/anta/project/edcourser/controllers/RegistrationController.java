package anta.project.edcourser.controllers;

import anta.project.edcourser.config.rest.model.MvcResponse;
import anta.project.edcourser.config.rest.resources.Statuses;
import anta.project.edcourser.dto.authorization.UserRegistration;
import anta.project.edcourser.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @PostMapping(value = "/registration")
    public MvcResponse registration(@RequestBody UserRegistration data) {
        userService.register(data);
        return new MvcResponse(Statuses.Ok);
    }
}
