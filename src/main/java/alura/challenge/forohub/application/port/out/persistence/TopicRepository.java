package alura.challenge.forohub.application.port.out.persistence;

import alura.challenge.forohub.domain.topic.Topic;
import alura.challenge.forohub.domain.topic.TopicId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface TopicRepository {
    boolean existsTopicById(TopicId id);
    boolean isTopicDuplicated(String title, String content);
    Page<Topic> findAllTopics(Pageable pagination);
    Optional<Topic> findTopicById(TopicId topicId);
    Topic updateTopic(Topic topic);
    Topic saveNew(Topic topic);
    void deleteTopicById(TopicId topicId);
}
