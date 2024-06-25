package alura.challenge.forohub.infrastructure.adapter.out.persistence.database.model;

import alura.challenge.forohub.domain.response.Response;
import alura.challenge.forohub.domain.response.ResponseId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "Response")
@Table(name = "response")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Accessors(fluent = true)
public class ResponseData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String message;

    private boolean isSolution;

    @NotNull
    private LocalDateTime creationDate;

    @NotNull
    private LocalDateTime lastEditDate;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private TopicData topic;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData author;

    public Response toDomain(){
        return Response.builder()
                .id(new ResponseId(this.id))
                .message(this.message)
                .isSolution(this.isSolution)
                .creationDate(this.creationDate)
                .lastEditDate(this.lastEditDate)
                .topic(this.topic.toDomain())
                .author(this.author.toDomain())
                .build();
    }

    public static ResponseData fromDomain(Response response){
        return ResponseData.builder()
                .id(Objects.isNull(response.id()) ? null : response.id().value())
                .message(response.message())
                .isSolution(response.isSolution())
                .creationDate(response.creationDate())
                .lastEditDate(response.lastEditDate())
                .topic(TopicData.fromDomain(response.topic()))
                .author(UserData.fromDomain(response.author()))
                .build();
    }
}
