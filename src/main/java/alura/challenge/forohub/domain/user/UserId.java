package alura.challenge.forohub.domain.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UserId(
        @Positive @NotNull Long value
) {}
