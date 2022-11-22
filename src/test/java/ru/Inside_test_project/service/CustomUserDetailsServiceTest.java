package ru.Inside_test_project.service;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import ru.Inside_test_project.AbstractTest;
import ru.Inside_test_project.MatcherFactory;
import ru.Inside_test_project.model.User;
import ru.Inside_test_project.repository.MyUserRepository;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.Inside_test_project.controller.TestDataControllersUtil.USER_NAME_NOT_CONSIST;
import static ru.Inside_test_project.service.TestDataServise.NEW_USER;

class CustomUserDetailsServiceTest extends AbstractTest {
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    MyUserRepository myUserRepository;
    /**
     * return userDetails for user by userName.
     */
    @Test
    void loadUserByUsername() {

        // build expected expectedUserwithDetails
        UserDetails expectedUserDetails =
                org.springframework.security.core.userdetails.User.builder()
                .username(NEW_USER.getName())
                .password(NEW_USER.getPassword())
                .roles(NEW_USER.getRole())
                .build();

        MatcherFactory.usingEqualsComparator( UserDetails.class)
                .assertMatch(customUserDetailsService.loadUserByUsername("user"),expectedUserDetails);

    }
        @Test
        void loadUserByUsernameNotConsist() throws EntityNotFoundException{
            Assert.assertThrows(EntityNotFoundException.class, () -> myUserRepository.findByName(USER_NAME_NOT_CONSIST)
                    .orElseThrow(EntityNotFoundException::new));
    }
}