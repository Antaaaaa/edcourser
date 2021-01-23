package anta.project.edcourser.services.user;

import anta.project.edcourser.models.sql.user.UserVerificationInfo;
import org.springframework.stereotype.Service;

@Service
public interface UserVerificationInfoService {
    UserVerificationInfo save(UserVerificationInfo userVerificationInfo);
}
