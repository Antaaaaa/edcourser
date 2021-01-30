package anta.project.edcourser.persistence.implementation.course;

import anta.project.edcourser.models.sql.course.CourseInfo;
import anta.project.edcourser.persistence.repositories.course.CourseInfoRepository;
import anta.project.edcourser.services.course.CourseInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class CourseInfoServiceImplementation implements CourseInfoService {

    private final CourseInfoRepository courseInfoRepository;

    @Override
    public CourseInfo save(CourseInfo courseInfo) {
        return courseInfoRepository.save(courseInfo);
    }
}
