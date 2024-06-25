package alura.challenge.forohub.application.port.in.usecase;

import alura.challenge.forohub.application.exceptions.DuplicatedTopicException;
import alura.challenge.forohub.application.exceptions.NotFoundException;
import alura.challenge.forohub.application.exceptions.UnauthorizedUserException;
import alura.challenge.forohub.domain.exceptions.BlankContentException;
import alura.challenge.forohub.domain.exceptions.BlankTitleException;
import alura.challenge.forohub.domain.exceptions.ClosedTopicException;
import alura.challenge.forohub.domain.topic.Topic;
import alura.challenge.forohub.domain.topic.TopicId;
import alura.challenge.forohub.domain.user.UserId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface EditTopicUseCase {
    Topic editTopic(@NotNull @Valid TopicId topicId,
                    @NotBlank String topicTitle,
                    @NotBlank String topicContent,
                    @NotNull @Valid UserId authenticatedUserId
    ) throws NotFoundException, DuplicatedTopicException, ClosedTopicException, BlankContentException, BlankTitleException, UnauthorizedUserException;
}
