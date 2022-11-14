package ru.Inside_test_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.Inside_test_project.model.Message;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    /**
     * @return ten last message from DB.
     */
    @Query(value = "SELECT * FROM message ORDER BY ID DESC LIMIT 10", nativeQuery = true)
    List<Message> getTenLastMessage();
}

