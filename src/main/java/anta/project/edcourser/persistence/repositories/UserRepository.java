package anta.project.edcourser.persistence.repositories;

import anta.project.edcourser.enums.UserRole;
import anta.project.edcourser.models.sql.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    Optional<List<User>> findAllByUserRole(UserRole userRole);
}
