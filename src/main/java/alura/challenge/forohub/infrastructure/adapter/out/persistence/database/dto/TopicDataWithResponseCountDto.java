package alura.challenge.forohub.infrastructure.adapter.out.persistence.database.dto;

import alura.challenge.forohub.domain.topic.Topic;
import alura.challenge.forohub.domain.topic.TopicId;
import alura.challenge.forohub.domain.topic.TopicStatus;
import alura.challenge.forohub.infrastructure.adapter.out.persistence.database.model.CourseData;
import alura.challenge.forohub.infrastructure.adapter.out.persistence.database.model.UserData;

import java.time.LocalDateTime;

public record TopicDataWithResponseCountDto(
        Long id,
        String title,
        String content,
        LocalDateTime creationDate,
        LocalDateTime lastEditDate,
        TopicStatus status,
        CourseData course,
        UserData author,
        Long responseCount,
        Boolean isSolved
) {
    public Topic toDomain(){
        return Topic.builder()
                .id(new TopicId(id))
                .title(title)
                .content(content)
                .creationDate(creationDate)
                .lastEditDate(lastEditDate)
                .status(status)
                .author(author.toDomain())
                .course(course.toDomain())
                .responsesCount(responseCount)
                .isSolved(isSolved)
                .build();
    }
}
