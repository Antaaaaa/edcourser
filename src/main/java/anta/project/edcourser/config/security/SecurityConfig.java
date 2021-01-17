package anta.project.edcourser.config.security;

import anta.project.edcourser.config.security.token.jwt.JwtTokenProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Service;

/**
 * Spring Security configuration
 *
 * @author Anatoliy
 * @version 0.1
 */

@Service
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    private static final String LOGIN_ENDPOINT = "/api/authorization";
    private static final String REGISTRATION_ENDPOINT = "/api/registration";
    private static final String RESTORE_PASSWORD = "/api/restore_password/**";

}
