package alura.challenge.forohub.application.port.in.usecase;

import alura.challenge.forohub.application.exceptions.NotFoundException;
import alura.challenge.forohub.domain.exceptions.ClosedTopicException;
import alura.challenge.forohub.domain.topic.TopicId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CloseTopicUseCase {
    void closeTopic(@NotNull @Valid TopicId topicId) throws NotFoundException, ClosedTopicException;
}
