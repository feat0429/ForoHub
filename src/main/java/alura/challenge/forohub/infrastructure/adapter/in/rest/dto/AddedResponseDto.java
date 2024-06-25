package alura.challenge.forohub.infrastructure.adapter.in.rest.dto;

import alura.challenge.forohub.domain.response.Response;

import java.time.LocalDateTime;

public record AddedResponseDto(
        Long id,
        String message,
        LocalDateTime creationDate,
        LocalDateTime lastEditDate,
        GetTopicWithResponseCountDto topic,
        String author
) {
    public static AddedResponseDto fromDomainModel(Response response) {
        return new AddedResponseDto(
                response.id().value(),
                response.message(),
                response.creationDate(),
                response.lastEditDate(),
                GetTopicWithResponseCountDto.fromDomainModel(response.topic()),
                response.author().name()
        );
    }
}
