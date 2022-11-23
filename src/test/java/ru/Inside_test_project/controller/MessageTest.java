package ru.Inside_test_project.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.Inside_test_project.AbstractTest;
import ru.Inside_test_project.security.JWTUtil;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.Inside_test_project.controller.TestDataControllersUtil.*;

class MessageTest extends AbstractTest {
    @Autowired
    JWTUtil jwtUtil;

    /**
     * If token is valid, message from user add in DB, return List<Message> with one received message , HTTP status 201.
     */

    @Test
    @WithUserDetails("admin")
    void receiveAnyMessage() throws Exception {
        mockMvc.perform(post("/message")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(ANY_MESSAGE_JSON_STRING)
                        .accept(APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[0].message").value(ANY_MESSAGE))
                .andExpect(jsonPath("$.[0].id").isNumber())
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    /**
     * if message = "history 10" @return List<Message> , HTTP status 200.
     */
    @Test
    @WithUserDetails("admin")
    void receiveHistoryMessage() throws Exception {
        mockMvc.perform(post("/message")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(HISTORY_10_MESSAGE_JSON_STRING)
                        .accept(APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").isArray())
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * Try get Unauthorized user.
     * throws Exception
     */
    @Test
    void getUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get("/user"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    /**
     * authorized user with role admin and user can GetMapping request and return "User".
     */
    @Test
    @WithUserDetails(value = "user")
    void user() throws Exception {
        mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(USER_NAME))
                .andReturn();
    }

    /**
     * authorized user with role admin can GetMapping request and return "Admin".
     */
    @Test
    @WithUserDetails(value = "admin")
    void admin() throws Exception {
       mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(ADMIN_NAME))
                .andReturn();
    }
}