package alura.challenge.forohub.application.service;

import alura.challenge.forohub.application.exceptions.DuplicatedTopicException;
import alura.challenge.forohub.application.exceptions.NotFoundException;
import alura.challenge.forohub.application.exceptions.UnauthorizedUserException;
import alura.challenge.forohub.application.port.in.usecase.CloseTopicUseCase;
import alura.challenge.forohub.application.port.in.usecase.EditTopicUseCase;
import alura.challenge.forohub.application.port.out.persistence.TopicRepository;
import alura.challenge.forohub.domain.exceptions.BlankContentException;
import alura.challenge.forohub.domain.exceptions.BlankTitleException;
import alura.challenge.forohub.domain.exceptions.ClosedTopicException;
import alura.challenge.forohub.domain.topic.Topic;
import alura.challenge.forohub.domain.topic.TopicId;
import alura.challenge.forohub.domain.user.UserId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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
public class UpdateTopicService implements EditTopicUseCase, CloseTopicUseCase {
    private final TopicRepository topicRepository;

    @Override
    public Topic editTopic(@NotNull @Valid TopicId topicId, @NotBlank String topicTitle, @NotBlank String topicContent, @NotNull @Valid UserId authenticatedUserId) throws NotFoundException, DuplicatedTopicException, ClosedTopicException, BlankContentException, BlankTitleException, UnauthorizedUserException {
        var topic = topicRepository.findTopicById(topicId);
        var isTopicDuplicated = topicRepository.isTopicDuplicated(topicTitle,topicContent);

        if(topic.isEmpty()) throw  new NotFoundException("The specified Topic was not found.");
        if(topic.get().isClosed()) throw new ClosedTopicException("Topic is already closed and cannot be edited.");
        if(isTopicDuplicated) throw new DuplicatedTopicException("A topic with the same title and content already exists.");

        if(!topic.get().author().id().equals(authenticatedUserId)) throw new UnauthorizedUserException("The authenticated user ID must match the author ID of the Topic.");

        if(!topicTitle.equals(topic.get().title()) && !topicTitle.isBlank()) {
            topic.get().editTitle(topicTitle);
        }

        if(!topicContent.equals(topic.get().content()) && !topicContent.isBlank()) {
            topic.get().editContent(topicContent);
        }

        return topicRepository.updateTopic(topic.get());
    }

    @Override
    public void closeTopic(@NotNull @Valid TopicId topicId) throws NotFoundException, ClosedTopicException {
        var topic = topicRepository.findTopicById(topicId);

        if(topic.isEmpty()) throw  new NotFoundException("The specified Topic was not found.");

        topic.get().closeTopic();

        topicRepository.updateTopic(topic.get());
    }
}
