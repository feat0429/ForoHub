package alura.challenge.forohub.application.port.in.usecase;

import alura.challenge.forohub.application.exceptions.NotFoundException;
import alura.challenge.forohub.domain.response.Response;
import alura.challenge.forohub.domain.topic.TopicId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface FindResponsesByTopicUseCase {
    List<Response> findResponsesByTopic(@NotNull @Valid TopicId topicId) throws NotFoundException;
}
