package anta.project.edcourser.persistence.repositories.user;

import anta.project.edcourser.models.sql.user.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
    Optional<UserToken> findByUserId(Long userId);
    boolean existsByUserId(Long userId);
}
