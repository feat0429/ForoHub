package alura.challenge.forohub.application.port.out.persistence;

import alura.challenge.forohub.domain.course.Course;
import alura.challenge.forohub.domain.course.CourseId;

import java.util.Optional;

public interface CourseRepository {
    Optional<Course> findCourseById(CourseId courseId);
}
