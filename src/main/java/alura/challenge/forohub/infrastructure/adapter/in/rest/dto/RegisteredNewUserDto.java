package alura.challenge.forohub.infrastructure.adapter.in.rest.dto;

import alura.challenge.forohub.domain.user.User;

public record RegisteredNewUserDto(
        Long id,
        String name,
        String email
) {
    public static RegisteredNewUserDto fromDomain(User user){
        return new RegisteredNewUserDto(
                user.id().value(),
                user.name(),
                user.email()
        );
    }
}
