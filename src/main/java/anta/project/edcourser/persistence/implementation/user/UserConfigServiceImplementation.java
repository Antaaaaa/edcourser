package anta.project.edcourser.persistence.implementation.user;

import anta.project.edcourser.models.sql.user.UserConfig;
import anta.project.edcourser.persistence.repositories.user.UserConfigRepository;
import anta.project.edcourser.services.user.UserConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserConfigServiceImplementation implements UserConfigService {

    private final UserConfigRepository userConfigRepository;

    @Override
    public UserConfig save(UserConfig userConfig) {
        return userConfigRepository.save(userConfig);
    }
}
