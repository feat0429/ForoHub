package alura.challenge.forohub.application.service;

import alura.challenge.forohub.application.exceptions.RegisteredUserException;
import alura.challenge.forohub.application.port.in.usecase.RegisterNewUserUseCase;
import alura.challenge.forohub.application.port.out.persistence.UserRepository;
import alura.challenge.forohub.domain.user.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RegisterUserService implements RegisterNewUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerNewUser(@NotNull @Valid User user) throws RegisteredUserException {
        if(userRepository.isUserEmailRegistered(user.email())) throw new RegisteredUserException("User "+ user.getUsername() + " with email " + user.email() + " is already registered.");

        user.password(passwordEncoder.encode(user.getPassword()));

        return userRepository.registerNewUser(user);
    }
}
