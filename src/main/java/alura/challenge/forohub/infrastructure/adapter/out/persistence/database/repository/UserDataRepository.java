package alura.challenge.forohub.infrastructure.adapter.out.persistence.database.repository;

import alura.challenge.forohub.infrastructure.adapter.out.persistence.database.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDataRepository extends JpaRepository<UserData, Long> {
    Optional<UserData> findByEmail(String email);
    boolean existsByEmail(String email);
}
