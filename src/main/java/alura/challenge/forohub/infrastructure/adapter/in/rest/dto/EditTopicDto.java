package alura.challenge.forohub.infrastructure.adapter.in.rest.dto;

public record EditTopicDto(
        String title,
        String content,
        Long topicId
) {}
