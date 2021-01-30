package anta.project.edcourser.persistence.implementation.user;

import anta.project.edcourser.config.security.token.jwt.JwtTokenProvider;
import anta.project.edcourser.dto.authorization.UserAuthorization;
import anta.project.edcourser.dto.authorization.UserRegistration;
import anta.project.edcourser.enums.UserRole;
import anta.project.edcourser.enums.UserVerificationStatus;
import anta.project.edcourser.exceptions.authorization.NotValidRegistrationDataException;
import anta.project.edcourser.exceptions.authorization.UserCreationException;
import anta.project.edcourser.exceptions.authorization.UserIsBannedException;
import anta.project.edcourser.exceptions.authorization.UserNotFoundException;
import anta.project.edcourser.exceptions.data.ChangeEmailDataException;
import anta.project.edcourser.exceptions.data.ChangePaswordDataException;
import anta.project.edcourser.models.sql.user.*;
import anta.project.edcourser.persistence.repositories.user.UserRepository;
import anta.project.edcourser.services.user.*;
import anta.project.edcourser.utils.Validator;
import anta.project.edcourser.utils.email.EmailSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final UserInfoService userInfoService;
    private final UserTokenService userTokenService;
    private final UserConfigService userConfigService;
    private final UserVerificationInfoService userVerificationInfoService;
    private final EmailSender emailSender;

    private JwtTokenProvider jwtTokenProvider;
    private AuthenticationManager manager;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void setCircularDependencies(@Lazy JwtTokenProvider jwtTokenProvider,
                       @Lazy AuthenticationManager authenticationManager,
                       @Lazy BCryptPasswordEncoder passwordEncoder) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.manager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(String.format("No user with email: %s", email)));
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format("No user with id: %s", id)));
    }

    @Override
    public User register(UserRegistration userDTO) {
        User user = userRepository.save(createUser(userDTO).orElseThrow(() -> new UserCreationException("It is not possible to create such user")));
        createDefaultUserInfo(user, userDTO);
        createDefaultUserConfig(user);
        createDefaultUserVerificationInfo(user);
        return user;
    }

    @Override
    public Map<String, String> login(UserAuthorization authorization) {
        manager.authenticate(new UsernamePasswordAuthenticationToken(
                authorization.getEmail(), authorization.getPassword()));
        User user = findByEmail(authorization.getEmail());
        checkIfNotBanned(user);

        String token = jwtTokenProvider.createToken(user.getEmail());
        saveUserToken(user, token);

        return Map.of("email", user.getEmail(), "token", token);
    }

    private Optional<User> createUser(UserRegistration userDTO) {
        validateUser(userDTO);
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setUserRole(UserRole.ROLE_USER);
        user.setCreated(new Date().getTime());
        return Optional.of(user);
    }

    private void validateUser(UserRegistration userDTO) {
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

    private void createDefaultUserInfo(User user, UserRegistration registrationDTO) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUser(user);
        userInfo.setName(registrationDTO.getName());
        userInfo.setNickName(registrationDTO.getNickName());
        userInfo.setCountry(registrationDTO.getCountry());
        userInfo.setPhone(registrationDTO.getPhone());
        user.setUserInfo(userInfo);
        userInfoService.save(userInfo);
        userRepository.save(user);
    }

    private void createDefaultUserConfig(User user) {
        UserConfig userConfig = new UserConfig();
        userConfig.setUser(user);
        userConfig.setVerificationStatus(UserVerificationStatus.NOT_SEND);
        user.setUserConfig(userConfig);
        userConfigService.save(userConfig);
        userRepository.save(user);
    }

    private void createDefaultUserVerificationInfo(User user) {
        UserVerificationInfo userVerificationInfo = new UserVerificationInfo();
        userVerificationInfo.setUser(user);
        user.setUserVerificationInfo(userVerificationInfo);
        userVerificationInfoService.save(userVerificationInfo);
        userRepository.save(user);
    }

    private void checkIfNotBanned(User user) {
        if (user.getUserConfig().isBanned()) {
            throw new UserIsBannedException("Such user is banned");
        }
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
        UserToken userToken;
        if (userTokenService.isPresent(user.getId())) {
            userToken = getTokenByUserEmail(user.getEmail());
        } else {
            userToken = new UserToken();
            userToken.setUser(user);
        }
        userToken.setToken(token);
        user.setUserToken(userToken);
        userTokenService.save(userToken);
        userRepository.save(user);
    }
}
