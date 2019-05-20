package io.github.demirmustafa.testcontainers.domain.repository;

import io.github.demirmustafa.testcontainers.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
