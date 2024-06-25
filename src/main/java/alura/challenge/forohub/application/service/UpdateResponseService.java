package alura.challenge.forohub.application.service;

import alura.challenge.forohub.application.exceptions.DuplicatedResponseException;
import alura.challenge.forohub.application.exceptions.NotFoundException;
import alura.challenge.forohub.application.exceptions.UnauthorizedUserException;
import alura.challenge.forohub.application.port.in.usecase.UpdateResponseUseCase;
import alura.challenge.forohub.application.port.out.persistence.ResponseRepository;
import alura.challenge.forohub.domain.exceptions.ClosedTopicException;
import alura.challenge.forohub.domain.response.Response;
import alura.challenge.forohub.domain.response.ResponseId;
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
public class UpdateResponseService implements UpdateResponseUseCase {
    private final ResponseRepository responseRepository;

    @Override
    public Response editResponseById(@NotNull @Valid ResponseId responseId, @NotBlank String message, @NotNull @Valid UserId authenticatedUserId) throws NotFoundException, ClosedTopicException, DuplicatedResponseException, UnauthorizedUserException {
        var response = responseRepository.findResponseById(responseId);

        if(response.isEmpty()) throw new NotFoundException("The specified Response was not found.");
        if(response.get().topic().isClosed()) throw new ClosedTopicException("Topic is already closed and does not accept mores responses.");
        if(!response.get().author().id().equals(authenticatedUserId)) throw new UnauthorizedUserException("The authenticated user ID must match the author ID specified in the Response.");
        if(responseRepository.isResponseDuplicated(response.get().topic().id(),message)) throw new DuplicatedResponseException("This Topic already has a Response with the same message.");

        if(!response.get().message().equals(message)){
            System.out.println(response.get().message());
            response.get().editMessage(message);
        }

        return responseRepository.updateResponse(response.get());
    }
}
