package alura.challenge.forohub.application.port.in.usecase;

import alura.challenge.forohub.application.exceptions.RegisteredUserException;
import alura.challenge.forohub.domain.user.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface RegisterNewUserUseCase {
    User registerNewUser(@NotNull @Valid User user) throws RegisteredUserException;
}
