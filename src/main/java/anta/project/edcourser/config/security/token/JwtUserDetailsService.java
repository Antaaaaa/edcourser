package anta.project.edcourser.config.security.token;

import anta.project.edcourser.config.security.token.jwt.JwtUser;
import anta.project.edcourser.config.security.token.jwt.JwtUserFactory;
import anta.project.edcourser.models.sql.user.User;
import anta.project.edcourser.services.user.UserService;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String email) {
        User user = userService.findByEmail(email);
        return JwtUserFactory.create(user);
    }
}
