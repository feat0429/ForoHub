package alura.challenge.forohub.infrastructure.adapter.in.rest.dto;

import alura.challenge.forohub.domain.response.Response;

import java.time.LocalDateTime;

public record GetResponseDto(
        Long id,
        String message,
        LocalDateTime creationDate,
        LocalDateTime lastEditDate,
        String author
) {
    public static GetResponseDto fromDomainModel(Response response) {
        return new GetResponseDto(
                response.id().value(),
                response.message(),
                response.creationDate(),
                response.lastEditDate(),
                response.author().name()
        );
    }
}
