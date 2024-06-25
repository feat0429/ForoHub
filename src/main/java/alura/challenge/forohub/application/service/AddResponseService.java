package alura.challenge.forohub.application.service;

import alura.challenge.forohub.application.exceptions.DuplicatedResponseException;
import alura.challenge.forohub.application.exceptions.NotFoundException;
import alura.challenge.forohub.application.exceptions.UnauthorizedUserException;
import alura.challenge.forohub.application.port.in.usecase.AddNewResponseUseCase;
import alura.challenge.forohub.application.port.out.persistence.ResponseRepository;
import alura.challenge.forohub.application.port.out.persistence.TopicRepository;
import alura.challenge.forohub.application.port.out.persistence.UserRepository;
import alura.challenge.forohub.domain.exceptions.ClosedTopicException;
import alura.challenge.forohub.domain.response.Response;
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
public class AddResponseService implements AddNewResponseUseCase {
    private final ResponseRepository responseRepository;
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    @Override
    public Response addNewResponseToTopic(@NotBlank String message, @NotNull @Valid UserId authorId, @NotNull @Valid TopicId topicId, @NotNull @Valid UserId authenticatedUserId) throws NotFoundException, ClosedTopicException, DuplicatedResponseException, UnauthorizedUserException {
        if(!authorId.equals(authenticatedUserId)) throw new UnauthorizedUserException("The authenticated user ID must match the author ID specified in the Response creation request.");

        var responseAuthor = userRepository.findUserById(authorId);
        var responseTopic = topicRepository.findTopicById(topicId);

        if(responseAuthor.isEmpty()) throw  new NotFoundException("The specified User was not found.");
        if(responseTopic.isEmpty()) throw  new NotFoundException("The specified Topic was not found.");
        if(responseTopic.get().isClosed()) throw  new ClosedTopicException("The Topic is already closed and accepts no more responses.");

        var isResponseDuplicated = responseRepository.isResponseDuplicated(topicId,message);

        if(isResponseDuplicated) throw new DuplicatedResponseException("There is a Response with the same message in this Topic.");


        return responseRepository.saveNew(Response.builder()
                .message(message)
                .author(responseAuthor.get())
                .topic(responseTopic.get())
                .build());
    }
}
