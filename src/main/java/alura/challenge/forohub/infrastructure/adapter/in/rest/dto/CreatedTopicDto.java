package alura.challenge.forohub.infrastructure.adapter.in.rest.dto;

import alura.challenge.forohub.domain.topic.Topic;
import alura.challenge.forohub.domain.topic.TopicStatus;

import java.time.LocalDateTime;

public record CreatedTopicDto(
        Long id,
        String title,
        String content,
        LocalDateTime creationDate,
        LocalDateTime lastEditDate,
        TopicStatus status,
        String course,
        String author
) {
    public static CreatedTopicDto fromDomainModel(Topic topic) {
        return new CreatedTopicDto(
                topic.id().value(),
                topic.title(),
                topic.content(),
                topic.creationDate(),
                topic.lastEditDate(),
                topic.status(),
                topic.course().name(),
                topic.author().name()
        );
    }
}
