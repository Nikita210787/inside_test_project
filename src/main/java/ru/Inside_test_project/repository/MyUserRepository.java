package ru.Inside_test_project.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.Inside_test_project.model.User;

import java.util.Optional;


@Repository
public interface MyUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
}
