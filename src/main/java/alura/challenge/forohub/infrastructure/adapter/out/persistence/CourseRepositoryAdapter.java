package alura.challenge.forohub.infrastructure.adapter.out.persistence;

import alura.challenge.forohub.application.port.out.persistence.CourseRepository;
import alura.challenge.forohub.commons.annotations.Adapter;
import alura.challenge.forohub.domain.course.Course;
import alura.challenge.forohub.domain.course.CourseId;
import alura.challenge.forohub.infrastructure.adapter.out.persistence.database.model.CourseData;
import alura.challenge.forohub.infrastructure.adapter.out.persistence.database.repository.CourseDataRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Adapter
@RequiredArgsConstructor
public class CourseRepositoryAdapter implements CourseRepository {

    private final CourseDataRepository courseDataRepository;

    @Override
    public Optional<Course> findCourseById(CourseId courseId) {
        return courseDataRepository.findById(courseId.value()).map(CourseData::toDomain);
    }
}
