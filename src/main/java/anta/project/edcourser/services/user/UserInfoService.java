package anta.project.edcourser.services.user;

import anta.project.edcourser.models.sql.user.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
public interface UserInfoService {
    UserInfo saveUserInfo(UserInfo userInfo);
}
