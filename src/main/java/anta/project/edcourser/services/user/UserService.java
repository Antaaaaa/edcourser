package anta.project.edcourser.services.user;

import anta.project.edcourser.dto.authorization.UserRegistrationDTO;
import anta.project.edcourser.exceptions.ChangePaswordDataException;
import anta.project.edcourser.exceptions.NotValidRegistrationDataException;
import anta.project.edcourser.models.sql.user.User;
import anta.project.edcourser.models.sql.user.UserToken;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    Optional<User> register(UserRegistrationDTO userDTO) throws NotValidRegistrationDataException;
    void changePassword(User user, String password) throws ChangePaswordDataException;
    void changeEmail(User user, String email);
    void changePassword(User user, String oldPassword, String newPassword);
    UserToken getTokenByUserEmail(String email);
    void saveUserToken(User user, String token);

}
