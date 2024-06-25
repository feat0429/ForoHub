package alura.challenge.forohub.infrastructure.adapter.out.persistence.database.model;

import alura.challenge.forohub.domain.topic.Topic;
import alura.challenge.forohub.domain.topic.TopicId;
import alura.challenge.forohub.domain.topic.TopicStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity(name = "Topic")
@Table(name = "topic")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder(toBuilder = true)
@Accessors(fluent = true)
public class TopicData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @Column(nullable = false)
    private LocalDateTime lastEditDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TopicStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData author;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseData course;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "topic_id")
    private List<ResponseData> responses;

    public Topic toDomain(){
        return Topic.builder()
                .id(new TopicId(this.id))
                .title(this.title)
                .content(this.content)
                .creationDate(this.creationDate)
                .lastEditDate(this.lastEditDate)
                .status(this.status)
                .author(this.author.toDomain())
                .course(this.course.toDomain())
                .build();
    }

    public static TopicData fromDomain(Topic topic){
        return TopicData.builder()
                .id(Objects.isNull(topic.id()) ? null : topic.id().value())
                .title(topic.title())
                .content(topic.content())
                .creationDate(topic.creationDate())
                .lastEditDate(topic.lastEditDate())
                .status(topic.status())
                .author(UserData.fromDomain(topic.author()))
                .course(CourseData.fromDomain(topic.course()))
                .build();
    }
}
