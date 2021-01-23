package anta.project.edcourser.controllers;

import anta.project.edcourser.config.rest.model.MvcResponse;
import anta.project.edcourser.config.rest.model.MvcResponseMap;
import anta.project.edcourser.config.rest.resources.Statuses;
import anta.project.edcourser.config.security.token.jwt.JwtTokenProvider;
import anta.project.edcourser.dto.authorization.UserAuthorization;
import anta.project.edcourser.models.sql.user.User;
import anta.project.edcourser.services.user.UserService;
import anta.project.edcourser.utils.email.EmailSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class AuthorizationController {

    private final UserService userService;

    @PostMapping(value = "/authorization")
    public MvcResponse login(@RequestBody UserAuthorization authorization){
        return new MvcResponseMap(Statuses.Ok, userService.login(authorization));
    }
}
