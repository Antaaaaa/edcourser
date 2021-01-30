package anta.project.edcourser.services.user;

import anta.project.edcourser.dto.authorization.UserAuthorization;
import anta.project.edcourser.dto.authorization.UserRegistration;
import anta.project.edcourser.models.sql.user.User;
import anta.project.edcourser.models.sql.user.UserToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {
    List<User> findAll();
    User findByEmail(String email);
    User findById(Long id);
    User register(UserRegistration userDTO);
    Map<String, String> login(UserAuthorization authorization);
    void changePassword(User user, String password);
    void changeEmail(User user, String email);
    void changePassword(User user, String oldPassword, String newPassword);
    UserToken getTokenByUserEmail(String email);
    void saveUserToken(User user, String token);

}
