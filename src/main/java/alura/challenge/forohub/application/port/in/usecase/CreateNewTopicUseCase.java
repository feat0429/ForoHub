package alura.challenge.forohub.application.port.in.usecase;

import alura.challenge.forohub.application.exceptions.DuplicatedTopicException;
import alura.challenge.forohub.application.exceptions.NotFoundException;
import alura.challenge.forohub.application.exceptions.UnauthorizedUserException;
import alura.challenge.forohub.domain.course.CourseId;
import alura.challenge.forohub.domain.topic.Topic;
import alura.challenge.forohub.domain.user.User;
import alura.challenge.forohub.domain.user.UserId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CreateNewTopicUseCase {
    Topic createNewTopic(@NotNull @Valid Topic topic,
                         @NotNull @Valid UserId authorId,
                         @NotNull @Valid CourseId courseId,
                         @NotNull @Valid UserId authenticatedUserId
    ) throws DuplicatedTopicException, NotFoundException, UnauthorizedUserException;
}
