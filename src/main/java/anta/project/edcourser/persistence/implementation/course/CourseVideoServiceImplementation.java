package anta.project.edcourser.persistence.implementation.course;

import anta.project.edcourser.models.sql.course.CourseInfo;
import anta.project.edcourser.models.sql.course.CourseVideo;
import anta.project.edcourser.persistence.repositories.course.CourseVideoRepository;
import anta.project.edcourser.services.course.CourseVideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class CourseVideoServiceImplementation implements CourseVideoService {

    private final CourseVideoRepository courseVideoRepository;

    @Override
    public CourseVideo save(CourseVideo courseVideo) {
        return courseVideoRepository.save(courseVideo);
    }
}
