package alura.challenge.forohub.domain.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ResponseId(@Positive @NotNull(message = "Id cannot be null.") Long value) {
}
