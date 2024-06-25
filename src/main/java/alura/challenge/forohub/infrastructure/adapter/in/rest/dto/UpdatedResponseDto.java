package alura.challenge.forohub.infrastructure.adapter.in.rest.dto;

import alura.challenge.forohub.domain.response.Response;

import java.time.LocalDateTime;

public record UpdatedResponseDto(
        Long id,
        String message,
        LocalDateTime creationDate,
        LocalDateTime lastEditDate,
        GetTopicWithResponseCountDto topic,
        String author
) {
    public static UpdatedResponseDto fromDomainModel(Response response) {
        return new UpdatedResponseDto(
                response.id().value(),
                response.message(),
                response.creationDate(),
                response.lastEditDate(),
                GetTopicWithResponseCountDto.fromDomainModel(response.topic()),
                response.author().name()
        );
    }
}
