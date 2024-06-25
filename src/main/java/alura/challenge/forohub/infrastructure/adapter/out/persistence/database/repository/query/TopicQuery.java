package alura.challenge.forohub.infrastructure.adapter.out.persistence.database.repository.query;

public final class TopicQuery {
    public final static String findAllTopicsWithResponseCount = "SELECT " +
            "new alura.challenge.forohub.infrastructure.adapter.out.persistence.database.dto.TopicDataWithResponseCountDto(" +
            "t.id, "+
            "t.title, " +
            "t.content, " +
            "t.creationDate, " +
            "t.lastEditDate, " +
            "t.status, " +
            "c, " +
            "a, " +
            "COUNT(r), " +
            "CASE WHEN (COUNT(CASE WHEN r.isSolution = True THEN r.id END) > 0) THEN True ELSE False END) " +
            "FROM Topic t " +
            "LEFT JOIN t.responses r " +
            "JOIN t.course c " +
            "JOIN t.author a " +
            "GROUP BY t.id";

    public final static String findTopicByIdWithResponseCount = "SELECT " +
            "new alura.challenge.forohub.infrastructure.adapter.out.persistence.database.dto.TopicDataWithResponseCountDto(" +
            "t.id, "+
            "t.title, " +
            "t.content, " +
            "t.creationDate, " +
            "t.lastEditDate, " +
            "t.status, " +
            "c, " +
            "a, " +
            "COUNT(r), " +
            "CASE WHEN (COUNT(CASE WHEN r.isSolution = True THEN r.id END) > 0) THEN True ELSE False END) " +
            "FROM Topic t " +
            "LEFT JOIN t.responses r " +
            "JOIN t.course c " +
            "JOIN t.author a " +
            "WHERE t.id = :topicId " +
            "GROUP BY t.id";
}
