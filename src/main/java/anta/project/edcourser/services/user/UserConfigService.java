package anta.project.edcourser.services.user;

import anta.project.edcourser.models.sql.user.UserConfig;
import org.springframework.stereotype.Service;

@Service
public interface UserConfigService {
    UserConfig save(UserConfig userConfig);
}
