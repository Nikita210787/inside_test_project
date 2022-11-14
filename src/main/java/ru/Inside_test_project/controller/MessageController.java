package ru.Inside_test_project.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.Inside_test_project.dto.MessageDTO;
import ru.Inside_test_project.model.Message;
import ru.Inside_test_project.model.User;
import ru.Inside_test_project.repository.MessageRepository;
import ru.Inside_test_project.repository.MyUserRepository;
import ru.Inside_test_project.service.MessageService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class MessageController {
    MessageService ms;
   /* @Autowired
    MyUserRepository myUserRepository;
    @Autowired
    MessageRepository messageRepository;*/

    @GetMapping("/")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/user")
    public String user() {
        return "User";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Admin";
    }

    @PostMapping("/message")
    public ResponseEntity<List<Message>> listenMessage(@RequestBody MessageDTO messageDTO) {
        log.info("message received");
        if (messageDTO == null || messageDTO.message().isEmpty() ) {
            throw new NullPointerException("Message cant be null");
        }
        // Check token in JWTFilter
        if (messageDTO.message().equals("history 10")) {
           return ms.getTenLastMessage();
        } else {
            return  ms.createMessage(messageDTO);
        }
    }

}
