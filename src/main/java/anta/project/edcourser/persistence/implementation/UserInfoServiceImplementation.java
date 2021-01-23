package anta.project.edcourser.persistence.implementation;

import anta.project.edcourser.models.sql.user.UserInfo;
import anta.project.edcourser.persistence.repositories.UserInfoRepository;
import anta.project.edcourser.services.user.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserInfoServiceImplementation implements UserInfoService {

    private final UserInfoRepository userInfoRepository;


    @Override
    public UserInfo save(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }
}
