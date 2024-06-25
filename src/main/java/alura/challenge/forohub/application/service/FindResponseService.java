package alura.challenge.forohub.application.service;

import alura.challenge.forohub.application.exceptions.NotFoundException;
import alura.challenge.forohub.application.port.in.usecase.FindResponsesByTopicUseCase;
import alura.challenge.forohub.application.port.out.persistence.ResponseRepository;
import alura.challenge.forohub.application.port.out.persistence.TopicRepository;
import alura.challenge.forohub.domain.response.Response;
import alura.challenge.forohub.domain.topic.TopicId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
@Service
@RequiredArgsConstructor
@Validated
@Scope( proxyMode = ScopedProxyMode.TARGET_CLASS )
public class FindResponseService implements FindResponsesByTopicUseCase {
    private final ResponseRepository responseRepository;
    private final TopicRepository  topicRepository;

    @Override
    public List<Response> findResponsesByTopic(@NotNull @Valid TopicId topicId) throws NotFoundException {
        var isTopicStored = topicRepository.existsTopicById(topicId);

        if(!isTopicStored) throw  new NotFoundException("The specified Topic was not found.");

        return responseRepository.findResponsesByTopic(topicId);
    }
}
