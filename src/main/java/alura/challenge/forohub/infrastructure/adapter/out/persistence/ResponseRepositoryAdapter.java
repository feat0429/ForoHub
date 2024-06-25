package alura.challenge.forohub.infrastructure.adapter.out.persistence;

import alura.challenge.forohub.application.port.out.persistence.ResponseRepository;
import alura.challenge.forohub.commons.annotations.Adapter;
import alura.challenge.forohub.domain.response.Response;
import alura.challenge.forohub.domain.response.ResponseId;
import alura.challenge.forohub.domain.topic.TopicId;
import alura.challenge.forohub.infrastructure.adapter.out.persistence.database.model.ResponseData;
import alura.challenge.forohub.infrastructure.adapter.out.persistence.database.repository.ResponseDataRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Adapter
@RequiredArgsConstructor
public class ResponseRepositoryAdapter implements ResponseRepository {
    private final ResponseDataRepository responseRepository;

    @Override
    public List<Response> findResponsesByTopic(TopicId topicId) {
        return responseRepository.findByTopicId(topicId.value()).stream()
                .map(response -> Response.builder()
                        .id(new ResponseId(response.id()))
                        .message(response.message())
                        .isSolution(response.isSolution())
                        .creationDate(response.creationDate())
                        .lastEditDate(response.lastEditDate())
                        .author(response.author().toDomain())
                        .build())
                .toList();
    }

    @Override
    public Optional<Response> findResponseById(ResponseId responseId) {
        return responseRepository.findById(responseId.value()).map(ResponseData::toDomain);
    }

    @Override
    public Response updateResponse(Response response) {
        return responseRepository.save(ResponseData.fromDomain(response)).toDomain();
    }

    @Override
    public Response saveNew(Response response) {
        return responseRepository.save(ResponseData.fromDomain(response)).toDomain();
    }

    @Override
    public boolean isResponseDuplicated(TopicId topicId, String message) {
        return responseRepository.isResponseDuplicated(topicId.value(),message);
    }
}
