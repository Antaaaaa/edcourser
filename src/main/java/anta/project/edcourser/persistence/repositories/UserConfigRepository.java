package anta.project.edcourser.persistence.repositories;

import anta.project.edcourser.models.sql.user.UserConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserConfigRepository extends JpaRepository<UserConfig, Long> {}
