package alura.challenge.forohub.infrastructure.adapter.in.rest.dto;

import alura.challenge.forohub.domain.topic.Topic;
import alura.challenge.forohub.domain.topic.TopicStatus;

import java.time.LocalDateTime;

public record GetTopicWithResponseCountDto(
        Long id,
        String title,
        LocalDateTime creationDate,
        LocalDateTime lastEditDate,
        TopicStatus status,
        String course,
        String author,
        Long responsesCount,
        Boolean isSolved
) {
    public static GetTopicWithResponseCountDto fromDomainModel(Topic topic) {
        return new GetTopicWithResponseCountDto(
                topic.id().value(),
                topic.title(),
                topic.creationDate(),
                topic.lastEditDate(),
                topic.status(),
                topic.course().name(),
                topic.author().name(),
                topic.responsesCount(),
                topic.isSolved()
        );
    }
}
