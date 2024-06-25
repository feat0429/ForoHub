package alura.challenge.forohub.application.port.in.usecase;

import alura.challenge.forohub.domain.user.User;

public interface GenerateTokenUseCase {
    String generateToken(User user);
    String getSubject(String token);
}
