package ru.Inside_test_project.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.Inside_test_project.AbstractTest;
import ru.Inside_test_project.MatcherFactory;
import ru.Inside_test_project.security.JWTUtil;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.Inside_test_project.MatcherFactory.Matcher.getContent;
import static ru.Inside_test_project.controller.TestDataControllersUtil.*;

class MessageTest extends AbstractTest {
    @Autowired
    JWTUtil jwtUtil;
    /**
     * If token is valid, message from user add in DB, return List<Message> with one reseived message , HTTP status 201.
     * if message = "history 10" @return List<Message> , HTTP status 200.
     */

    @Test
    @WithUserDetails("admin")
    void reciveAnyMessage() throws Exception {
        MvcResult result = mockMvc.perform(post("/message")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(ANY_MESSAGE_JSON_STRING)
                        .accept(APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
               .andExpect(status().isCreated())
                .andReturn();
        MatcherFactory.usingEqualsComparator(String.class)
                .assertMatch(getContent(result).split("\"")[5],ANY_MESSAGE);
    }
    @Test
    @WithUserDetails("admin")
    void reciveHistoryMessage() throws Exception {
        MvcResult result = mockMvc.perform(post("/message")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(HISTORY_10_MESSAGE_JSON_STRING)
                        .accept(APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
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
        perform(MockMvcRequestBuilders.get( "/user"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
    /**
     * authorized user with role admin and user can GetMapping request and return "User".
     */
    @Test
    @WithUserDetails(value = "user")
    void user() throws Exception {
        MvcResult result = mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        MatcherFactory.usingEqualsComparator(String.class).assertMatch(getContent(result).toLowerCase(),USER_NAME);
    }

    /**
     * authorized user with role admin can GetMapping request and return "Admin".
     */
    @Test
    @WithUserDetails(value = "admin")
    void admin() throws Exception {
        MvcResult result = mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        MatcherFactory.usingEqualsComparator(String.class).assertMatch(getContent(result).toLowerCase(),ADMIN_NAME);
    }
}