package alura.challenge.forohub.application.port.out.persistence;

import alura.challenge.forohub.domain.response.Response;
import alura.challenge.forohub.domain.response.ResponseId;
import alura.challenge.forohub.domain.topic.TopicId;

import java.util.List;
import java.util.Optional;

public interface ResponseRepository {
    List<Response> findResponsesByTopic(TopicId topicId);
    Optional<Response> findResponseById(ResponseId responseId);
    Response updateResponse(Response response);
    Response saveNew(Response response);
    boolean isResponseDuplicated(TopicId topicId,String message);
}
