package alura.challenge.forohub.application.port.out.persistence;

import alura.challenge.forohub.domain.user.User;
import alura.challenge.forohub.domain.user.UserId;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findUserById(UserId userId);
    Optional<User> findUserByEmail(String email);
    boolean isUserEmailRegistered(String email);
    User registerNewUser(User user);
}
