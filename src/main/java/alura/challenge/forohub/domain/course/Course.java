package alura.challenge.forohub.domain.course;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(fluent = true)
public class Course {
    @Valid
    private final CourseId id;
    @NotBlank
    private String name;
    @NotBlank
    private String category;
}
