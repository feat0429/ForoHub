package alura.challenge.forohub.domain.topic;

import alura.challenge.forohub.domain.course.Course;
import alura.challenge.forohub.domain.exceptions.*;
import alura.challenge.forohub.domain.user.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Accessors(fluent = true)
@Builder
@Getter
public class Topic {
    @Valid
    private final TopicId id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @Builder.Default
    @NotNull
    private final LocalDateTime creationDate = LocalDateTime.now();

    @Builder.Default
    @NotNull
    private LocalDateTime lastEditDate = LocalDateTime.now();

    @Builder.Default
    @NotNull
    private TopicStatus status = TopicStatus.OPEN;

    @Setter
    @Valid
    private User author;

    @Setter
    @Valid
    private Course course;

    @Builder.Default
    @NotNull
    private Long responsesCount = 0L;

    @Builder.Default
    @NotNull
    private Boolean isSolved = false;

    public void closeTopic() throws ClosedTopicException {
        if(this.status.equals(TopicStatus.CLOSED)){
            throw new ClosedTopicException("Topic is already closed.");
        }

        this.status = TopicStatus.CLOSED;
    }

    public boolean isClosed(){
        return this.status.equals(TopicStatus.CLOSED);
    }

    public void editTitle(String title) throws ClosedTopicException, BlankTitleException {
        if(this.status.equals(TopicStatus.CLOSED)) throw new ClosedTopicException("Topic is already closed.");
        if(this.title.isBlank()) throw new BlankTitleException("Title cannot be blank. Please enter a valid title.");

        this.title=title;
        this.lastEditDate = LocalDateTime.now();
    }

    public void editContent(String content) throws ClosedTopicException, BlankContentException {
        if(this.status.equals(TopicStatus.CLOSED)) throw new ClosedTopicException("Topic is already closed.");
        if(this.content.isBlank()) throw new BlankContentException("Content cannot be blank. Please enter a valid topic content.");


        this.content=content;
        this.lastEditDate = LocalDateTime.now();
    }
}
