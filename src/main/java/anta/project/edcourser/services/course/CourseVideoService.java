package anta.project.edcourser.services.course;

import anta.project.edcourser.models.sql.course.CourseVideo;
import org.springframework.stereotype.Service;

@Service
public interface CourseVideoService {
    CourseVideo save(CourseVideo courseVideo);
}
