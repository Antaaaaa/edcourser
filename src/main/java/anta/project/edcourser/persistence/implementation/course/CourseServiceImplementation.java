package anta.project.edcourser.persistence.implementation.course;

import anta.project.edcourser.exceptions.course.CourseNotFoundException;
import anta.project.edcourser.models.sql.course.Course;
import anta.project.edcourser.persistence.repositories.course.CourseRepository;
import anta.project.edcourser.services.course.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@Log4j2
@RequiredArgsConstructor
public class CourseServiceImplementation implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findByName(String name) {
        return courseRepository
                .findByName(name)
                .orElseThrow(() -> new CourseNotFoundException(format("Course %s not found", name)));
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }
}
