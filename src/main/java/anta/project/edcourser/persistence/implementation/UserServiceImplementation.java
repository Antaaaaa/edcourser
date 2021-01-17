package anta.project.edcourser.persistence.implementation;

import anta.project.edcourser.config.security.token.jwt.JwtTokenProvider;
import anta.project.edcourser.dto.authorization.UserRegistrationDTO;
import anta.project.edcourser.enums.UserRole;
import anta.project.edcourser.exceptions.*;
import anta.project.edcourser.models.sql.user.User;
import anta.project.edcourser.models.sql.user.UserInfo;
import anta.project.edcourser.models.sql.user.UserToken;
import anta.project.edcourser.persistence.repositories.UserRepository;
import anta.project.edcourser.services.user.UserInfoService;
import anta.project.edcourser.services.user.UserService;
import anta.project.edcourser.services.user.UserTokenService;
import anta.project.edcourser.utils.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserInfoService userInfoService;
    private final UserTokenService userTokenService;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> register(UserRegistrationDTO userDTO) {
        User user = userRepository.save(createUser(userDTO).orElseThrow(() -> new UserCreationException("It is not possible to create such user")));
        userInfoService.saveUserInfo(createDefaultUserInfo(user, userDTO));
        return Optional.of(user);
    }

    private Optional<User> createUser(UserRegistrationDTO userDTO) {
        validateUser(userDTO);
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setUserRole(UserRole.ROLE_USER);
        user.setCreated(new Date().getTime());
        return Optional.of(user);
    }

    private void validateUser(UserRegistrationDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new NotValidRegistrationDataException("Such email already using");
        }
        if (!Validator.isValidEmail(userDTO.getEmail())) {
            throw new NotValidRegistrationDataException("Not valid email");
        }
        if (!Validator.isValidPassword(userDTO.getPassword())) {
            throw new NotValidRegistrationDataException("Not valid password");
        }
    }

    private UserInfo createDefaultUserInfo(User user, UserRegistrationDTO registrationDTO) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setName(registrationDTO.getName());
        userInfo.setNickName(registrationDTO.getNickName());
        userInfo.setCountry(registrationDTO.getCountry());
        userInfo.setPhone(registrationDTO.getPhone());
        return userInfo;
    }

    @Override
    public void changePassword(User user, String password) {
        if (!Validator.isValidPassword(password)) {
            throw new ChangePaswordDataException("Not valid password");
        }
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public void changeEmail(User user, String email) {
        if (!Validator.isValidEmail(email)) {
            throw new ChangeEmailDataException("Not valid email");
        }
        user.setEmail(email);
        userRepository.save(user);
    }

    @Override
    public void changePassword(User user, String oldPassword, String newPassword) {
        validatePasswords(user, oldPassword, newPassword);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    private void validatePasswords(User user, String oldPassword, String newPassword) {
        if (!Validator.isValidPassword(oldPassword)) {
            throw new ChangePaswordDataException("Old password is not valid");
        }
        if (!Validator.isValidPassword(newPassword)) {
            throw new ChangePaswordDataException("New password is not valid");
        }

        if (!user.getPassword().equals(passwordEncoder.encode(oldPassword))) {
            throw new ChangePaswordDataException("Old password does not match");
        }
    }

    @Override
    public UserToken getTokenByUserEmail(String email) {
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Such user not found"));
        return userTokenService.findByUserId(user.getId());
    }

    @Override
    public void saveUserToken(User user, String token) {
        UserToken userToken = getTokenByUserEmail(user.getEmail());
        userToken.setToken(token);
        userTokenService.save(userToken);
    }
}
