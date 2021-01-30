package anta.project.edcourser.persistence.repositories.user;

import anta.project.edcourser.models.sql.user.UserVerificationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserVerificationInfoRepository extends JpaRepository<UserVerificationInfo, Long> {
}
