package anta.project.edcourser.services.course;

import anta.project.edcourser.models.sql.course.CourseInfo;
import org.springframework.stereotype.Service;

@Service
public interface CourseInfoService {
    CourseInfo save(CourseInfo courseInfo);
}
