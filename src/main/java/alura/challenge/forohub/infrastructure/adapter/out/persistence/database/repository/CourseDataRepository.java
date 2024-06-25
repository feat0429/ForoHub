package alura.challenge.forohub.infrastructure.adapter.out.persistence.database.repository;

import alura.challenge.forohub.infrastructure.adapter.out.persistence.database.model.CourseData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseDataRepository extends JpaRepository<CourseData, Long> {
}
