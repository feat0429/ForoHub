package alura.challenge.forohub.infrastructure.adapter.out.persistence.database.repository;

import alura.challenge.forohub.infrastructure.adapter.out.persistence.database.model.ResponseData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResponseDataRepository extends JpaRepository<ResponseData, Long> {
    List<ResponseData> findByTopicId(Long topicId);
    @Query("SELECT CASE WHEN(COUNT(r) > 0) THEN True ELSE False END " +
            "FROM Response r " +
            "JOIN r.topic t " +
            "WHERE t.id = :topicId " +
            "AND r.message = :message")
    boolean isResponseDuplicated(@Param("topicId") Long topicId, @Param("message") String message);
}
