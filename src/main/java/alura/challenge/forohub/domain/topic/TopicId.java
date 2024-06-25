package alura.challenge.forohub.domain.topic;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TopicId(
        @Positive @NotNull Long value
)
{}
