package by.baby.usermicroservice.repository;

import by.baby.usermicroservice.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
