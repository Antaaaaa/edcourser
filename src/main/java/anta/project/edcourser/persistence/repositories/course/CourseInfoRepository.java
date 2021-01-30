package anta.project.edcourser.persistence.repositories.course;

import anta.project.edcourser.models.sql.course.CourseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseInfoRepository extends JpaRepository<CourseInfo, Long> {
}
