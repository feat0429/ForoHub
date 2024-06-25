package alura.challenge.forohub.infrastructure.adapter.out.persistence;

import alura.challenge.forohub.application.port.out.persistence.TopicRepository;
import alura.challenge.forohub.commons.annotations.Adapter;
import alura.challenge.forohub.domain.topic.Topic;
import alura.challenge.forohub.domain.topic.TopicId;
import alura.challenge.forohub.infrastructure.adapter.out.persistence.database.dto.TopicDataWithResponseCountDto;
import alura.challenge.forohub.infrastructure.adapter.out.persistence.database.model.TopicData;
import alura.challenge.forohub.infrastructure.adapter.out.persistence.database.repository.TopicDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


@Adapter
@RequiredArgsConstructor
public class TopicRepositoryAdapter implements TopicRepository {
    private final TopicDataRepository topicRepository;

    @Override
    public boolean existsTopicById(TopicId id) {
        return topicRepository.existsById(id.value());
    }

    @Override
    public boolean isTopicDuplicated(String title, String content) {
        return topicRepository.existsByTitleAndContent(title,content);
    }

    @Override
    public Page<Topic> findAllTopics(Pageable pagination) {
        return topicRepository.findAllTopicsWithResponseCount(pagination)
                .map(TopicDataWithResponseCountDto::toDomain);
    }

    @Override
    public Optional<Topic> findTopicById(TopicId topicId) {
        return topicRepository.findTopicByIdWithResponseCount(topicId.value()).map(TopicDataWithResponseCountDto::toDomain);
    }

    @Override
    public Topic updateTopic(Topic topic) {
        return topicRepository.save(TopicData.fromDomain(topic)).toDomain();
    }

    @Override
    public Topic saveNew(Topic topic) {
        return topicRepository.save(TopicData.fromDomain(topic)).toDomain();
    }

    @Override
    public void deleteTopicById(TopicId topicId) {
        topicRepository.deleteById(topicId.value());
    }
}
