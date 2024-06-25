package alura.challenge.forohub.application.service;

import alura.challenge.forohub.application.port.in.usecase.GenerateTokenUseCase;
import alura.challenge.forohub.domain.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class TokenService implements GenerateTokenUseCase {
    @Value("${api.security.secret}")
    private String apiSecret;

    @Override
    public String generateToken(User user) {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);

            return JWT.create()
                    .withIssuer("ForoHub")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.id().value())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
    }

    public String getSubject(String token) {
        if(token.isBlank()) throw new RuntimeException("Token is null or empty.");

        DecodedJWT decodedJWT;

        Algorithm algorithm = Algorithm.HMAC256(apiSecret);

        decodedJWT = JWT.require(algorithm)
                .withIssuer("ForoHub")
                .build()
                .verify(token);

        if(decodedJWT == null) throw new RuntimeException("Invalid decoded JWT.");

        return decodedJWT.getSubject();
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-06:00"));
    }
}
