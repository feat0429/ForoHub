package alura.challenge.forohub.infrastructure.adapter.in.rest.dto;

public record AuthenticateUserDto(
        String email,
        String password
) {
}
