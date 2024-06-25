package alura.challenge.forohub.domain.course;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CourseId(@Positive @NotNull Long value) {
}
