package ru.Inside_test_project.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.Inside_test_project.model.User;
import ru.Inside_test_project.repository.MyUserRepository;

import javax.persistence.EntityNotFoundException;


@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private MyUserRepository dao;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("Load User by name");
        User myUser= dao.findByName(userName).orElseThrow( () -> new EntityNotFoundException("User with such name not exist"));
        if (myUser == null) {
            throw new UsernameNotFoundException("Unknown user: "+userName);
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(myUser.getName())
                .password(myUser.getPassword())
                .roles(myUser.getRole())
                .build();
    }
}
