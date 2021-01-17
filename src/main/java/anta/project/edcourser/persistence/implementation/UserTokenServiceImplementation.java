package anta.project.edcourser.persistence.implementation;

import anta.project.edcourser.exceptions.UserTokenNotFoundException;
import anta.project.edcourser.models.sql.user.User;
import anta.project.edcourser.models.sql.user.UserToken;
import anta.project.edcourser.persistence.repositories.UserTokenRepository;
import anta.project.edcourser.services.user.UserTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserTokenServiceImplementation implements UserTokenService {

    private final UserTokenRepository userTokenRepository;

    @Override
    public UserToken findByUserId(Long id) {
        return userTokenRepository.findByUserId(id).orElseThrow(() -> new UserTokenNotFoundException("Token not found"));
    }

    @Override
    public UserToken save(UserToken userToken) {
        return userTokenRepository.save(userToken);
    }
}
