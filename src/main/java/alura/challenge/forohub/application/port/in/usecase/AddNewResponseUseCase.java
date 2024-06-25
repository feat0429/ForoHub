package alura.challenge.forohub.application.port.in.usecase;

import alura.challenge.forohub.application.exceptions.DuplicatedResponseException;
import alura.challenge.forohub.application.exceptions.NotFoundException;
import alura.challenge.forohub.application.exceptions.UnauthorizedUserException;
import alura.challenge.forohub.domain.exceptions.ClosedTopicException;
import alura.challenge.forohub.domain.response.Response;
import alura.challenge.forohub.domain.topic.TopicId;
import alura.challenge.forohub.domain.user.UserId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface AddNewResponseUseCase {
    Response addNewResponseToTopic(@NotBlank String message,
                                   @NotNull @Valid  UserId userId,
                                   @NotNull @Valid  TopicId topicId,
                                   @NotNull @Valid UserId authenticatedUserId
    ) throws NotFoundException, ClosedTopicException, DuplicatedResponseException, UnauthorizedUserException;
}
