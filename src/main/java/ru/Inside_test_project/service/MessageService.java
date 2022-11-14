package ru.Inside_test_project.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.Inside_test_project.dto.MessageDTO;
import ru.Inside_test_project.model.Message;
import ru.Inside_test_project.model.User;
import ru.Inside_test_project.repository.MessageRepository;
import ru.Inside_test_project.repository.MyUserRepository;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MessageService {
    @Autowired
    MyUserRepository myUserRepository;
    @Autowired
    MessageRepository messageRepository;

    public ResponseEntity<List<Message>> createMessage(MessageDTO messageDTO) {
        log.info("created message");
        User user= myUserRepository.findByName(messageDTO.name()).orElseThrow( () -> new EntityNotFoundException("User with such name not exist"));

        Message newmessage = new Message();
        newmessage.setMessage(messageDTO.message());
        newmessage.setUser(user);

        messageRepository.save(newmessage);
        List<Message> lmONe=new ArrayList<>();
        lmONe.add(newmessage);
        return ResponseEntity.status(201).body(lmONe);
    }
    public ResponseEntity<List<Message>> getTenLastMessage(){
        //в случае успешной проверки токена отправить отправителю 10 последних сообщений из БД
        List<Message> lm = messageRepository.getTenLastMessage();
        return ResponseEntity.status(200).body(lm);
    }

}
