package alura.challenge.forohub.application.port.in.usecase;

import alura.challenge.forohub.domain.topic.Topic;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface FindAllTopicsUseCase {
    Page<Topic> findAllTopics(@NotNull Pageable pagination);
}
