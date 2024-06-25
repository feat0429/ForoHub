package alura.challenge.forohub.infrastructure.adapter.in.rest.dto;

import alura.challenge.forohub.domain.user.User;

public record RegisterNewUserDto(
        String name,
        String password,
        String email
) {
    public User toDomainModel(){
        return User.builder()
                .name(name)
                .password(password)
                .email(email)
                .build();
    }
}
