package alura.challenge.forohub.infrastructure.adapter.out.persistence.database.model;

import alura.challenge.forohub.domain.course.Course;
import alura.challenge.forohub.domain.course.CourseId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Entity(name = "Course")
@Table(name = "course")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Accessors(fluent = true)
public class CourseData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    private List<TopicData> topics;

    public Course toDomain(){
        return Course.builder()
                .id(new CourseId(this.id))
                .name(this.name)
                .category(this.category)
                .build();
    }

    public static CourseData fromDomain(Course course){
        return CourseData.builder()
                .id(course.id().value())
                .name(course.name())
                .category(course.category())
                .build();
    }
}
