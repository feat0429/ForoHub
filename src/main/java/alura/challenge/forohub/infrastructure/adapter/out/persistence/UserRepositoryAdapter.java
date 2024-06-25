package alura.challenge.forohub.infrastructure.adapter.out.persistence;

import alura.challenge.forohub.application.port.out.persistence.UserRepository;
import alura.challenge.forohub.commons.annotations.Adapter;
import alura.challenge.forohub.domain.user.User;
import alura.challenge.forohub.domain.user.UserId;
import alura.challenge.forohub.infrastructure.adapter.out.persistence.database.model.UserData;
import alura.challenge.forohub.infrastructure.adapter.out.persistence.database.repository.UserDataRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Adapter
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {
    private final UserDataRepository userDataRepository;

    @Override
    public Optional<User> findUserById(UserId userId) {
        return userDataRepository.findById(userId.value()).map(UserData::toDomain);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userDataRepository.findByEmail(email)
                .map(UserData::toDomain);
    }

    @Override
    public boolean isUserEmailRegistered(String email) {
        return userDataRepository.existsByEmail(email);
    }

    @Override
    public User registerNewUser(User user) {
        return userDataRepository.save(UserData.fromDomain(user)).toDomain();
    }
}
