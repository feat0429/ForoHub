package alura.challenge.forohub.infrastructure.adapter.out.persistence.database.model;

import alura.challenge.forohub.domain.user.User;
import alura.challenge.forohub.domain.user.UserId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Objects;

@Entity(name = "User")
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder(toBuilder = true)
@Accessors(fluent = true)
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<TopicData> topics;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<ResponseData> responses;

    public User toDomain(){
        return User.builder()
                .id(new UserId(this.id))
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .build();
    }

    public static UserData fromDomain(User user){
        return UserData.builder()
                .id(Objects.isNull(user.id()) ? null : user.id().value())
                .name(user.name())
                .email(user.email())
                .password(user.getPassword())
                .build();
    }
}
