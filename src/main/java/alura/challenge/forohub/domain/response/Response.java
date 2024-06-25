package alura.challenge.forohub.domain.response;

import alura.challenge.forohub.domain.topic.Topic;
import alura.challenge.forohub.domain.user.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Accessors(fluent = true)
@Builder
@Getter
public class Response {
    @Valid
    private final ResponseId id;

    @NotBlank
    private String message;

    @Builder.Default
    @NotNull
    private final LocalDateTime creationDate = LocalDateTime.now();

    @Builder.Default
    @NotNull
    private LocalDateTime lastEditDate = LocalDateTime.now();

    @Setter
    @Valid
    private User author;

    @Setter
    @Valid
    private Topic topic;

    @Setter
    @NotNull
    private boolean isSolution;

    public void editMessage(String message){
        this.message=message;
        this.lastEditDate = LocalDateTime.now();
    }
}
