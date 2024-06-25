package alura.challenge.forohub.application.service;

import alura.challenge.forohub.application.exceptions.NotFoundException;
import alura.challenge.forohub.application.port.in.usecase.DeleteTopicUseCase;
import alura.challenge.forohub.application.port.out.persistence.TopicRepository;
import alura.challenge.forohub.domain.topic.TopicId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
@Scope( proxyMode = ScopedProxyMode.TARGET_CLASS )
public class DeleteTopicService implements DeleteTopicUseCase {
   private final TopicRepository topicRepository;

    @Override
    public void deleteTopic(@NotNull @Valid TopicId topicId) throws NotFoundException {
        var topicExists = topicRepository.existsTopicById(topicId);

        if(!topicExists) throw  new NotFoundException("The specified Topic was not found.");

        topicRepository.deleteTopicById(topicId);
    }
}
