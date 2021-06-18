package ru.wt5n.microservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.wt5n.microservice.models.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);
}
