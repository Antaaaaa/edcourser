package anta.project.edcourser.persistence.implementation;

import anta.project.edcourser.models.sql.user.UserVerificationInfo;
import anta.project.edcourser.persistence.repositories.UserVerificationInfoRepository;
import anta.project.edcourser.services.user.UserVerificationInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserVerificationInfoImplementation implements UserVerificationInfoService {

    private final UserVerificationInfoRepository userVerificationInfoRepository;

    @Override
    public UserVerificationInfo save(UserVerificationInfo userVerificationInfo) {
        return userVerificationInfoRepository.save(userVerificationInfo);
    }
}
