package alura.challenge.forohub.application.service;

import alura.challenge.forohub.application.exceptions.NotFoundException;
import alura.challenge.forohub.application.port.in.usecase.FindAllTopicsUseCase;
import alura.challenge.forohub.application.port.in.usecase.FindTopicByIdUseCase;
import alura.challenge.forohub.application.port.out.persistence.TopicRepository;
import alura.challenge.forohub.domain.topic.Topic;
import alura.challenge.forohub.domain.topic.TopicId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
@RequiredArgsConstructor
@Validated
@Scope( proxyMode = ScopedProxyMode.TARGET_CLASS )
public class FindTopicService implements FindAllTopicsUseCase, FindTopicByIdUseCase {
    private final TopicRepository topicRepository;

    @Override
    public Page<Topic> findAllTopics(@NotNull Pageable pagination) {
        return  topicRepository.findAllTopics(pagination);
    }

    @Override
    public Topic findTopicById(@NotNull @Valid TopicId topicId) throws NotFoundException {
        var topic = topicRepository.findTopicById(topicId);

        if(topic.isEmpty()) throw new NotFoundException("The specified Topic was not found.");

        return topic.get();
    }
}
