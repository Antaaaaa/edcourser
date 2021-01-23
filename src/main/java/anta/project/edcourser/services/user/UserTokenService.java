package anta.project.edcourser.services.user;

import anta.project.edcourser.models.sql.user.User;
import anta.project.edcourser.models.sql.user.UserToken;
import org.springframework.stereotype.Service;

@Service
public interface UserTokenService {
    UserToken findByUserId(Long id);
    UserToken save(UserToken userToken);
    boolean isPresent(Long id);
}
