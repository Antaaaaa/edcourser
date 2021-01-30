package anta.project.edcourser.services.course;

import anta.project.edcourser.models.sql.course.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    List<Course> findAll();
    Course findByName(String name);
    Course save(Course course);
}
