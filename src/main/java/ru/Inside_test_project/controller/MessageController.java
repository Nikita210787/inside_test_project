package ru.Inside_test_project.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.Inside_test_project.dto.MessageDTO;
import ru.Inside_test_project.model.Message;
import ru.Inside_test_project.service.MessageService;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class MessageController {
    MessageService ms;

    /**
     * If token is valid, message from user add in DB, return List<Message> with one reseived message , HTTP status 201.
     * if message = "history 10" @return List<Message> , HTTP status 200.
     *
     * @param messageDTO
     * @return List<Message> , HTTP status.
     */
    @PostMapping("/message")
    public ResponseEntity<List<Message>> listenMessage(@RequestBody MessageDTO messageDTO) {
        log.info("message received");
        if (messageDTO == null || messageDTO.message().isEmpty()) {
            throw new NullPointerException("Message cant be null");
        }
        // Check token in JWTFilter
        if (messageDTO.message().equals("history 10")) {
            return ms.getTenLastMessage();
        } else {
            return ms.createMessage(messageDTO);
        }
    }

    /**
     * authorized user with role admin and user can GetMapping request and return "User".
     */
    @GetMapping("/user")
    public String user() {
        return "User";
    }

    /**
     * authorized user with role admin can GetMapping request and return "Admin".
     */
    @GetMapping("/admin")
    public String admin() {
        return "Admin";
    }


}
