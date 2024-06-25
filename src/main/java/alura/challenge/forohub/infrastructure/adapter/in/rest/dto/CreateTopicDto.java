package alura.challenge.forohub.infrastructure.adapter.in.rest.dto;

import alura.challenge.forohub.domain.topic.Topic;

public record CreateTopicDto(
        String title,
        String content,
        Long authorId,
        Long courseId
) {
    public Topic toDomainModel(){
        return Topic.builder()
                .title(title)
                .content(content)
                .build();
    }
}
