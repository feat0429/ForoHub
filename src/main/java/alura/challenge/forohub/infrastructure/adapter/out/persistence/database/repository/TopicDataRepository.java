package alura.challenge.forohub.infrastructure.adapter.out.persistence.database.repository;

import alura.challenge.forohub.infrastructure.adapter.out.persistence.database.dto.TopicDataWithResponseCountDto;
import alura.challenge.forohub.infrastructure.adapter.out.persistence.database.model.TopicData;
import alura.challenge.forohub.infrastructure.adapter.out.persistence.database.repository.query.TopicQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicDataRepository extends JpaRepository<TopicData, Long> {
    boolean existsByTitleAndContent(String title, String content);
    @Query(TopicQuery.findAllTopicsWithResponseCount)
    Page<TopicDataWithResponseCountDto> findAllTopicsWithResponseCount(Pageable pagination);

    @Query(TopicQuery.findTopicByIdWithResponseCount)
    Optional<TopicDataWithResponseCountDto> findTopicByIdWithResponseCount(@Param("topicId") Long topicId);
}