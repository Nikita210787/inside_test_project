package ru.Inside_test_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.Inside_test_project.model.Message;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    //TODO get last 10
    @Query("SELECT m FROM Message m")
    List<Message> getTenLastMessage();
}

