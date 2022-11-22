package ru.Inside_test_project.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import ru.Inside_test_project.AbstractTest;
import ru.Inside_test_project.MatcherFactory;
import ru.Inside_test_project.security.JWTUtil;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.Inside_test_project.MatcherFactory.Matcher.getContent;
import static ru.Inside_test_project.controller.TestDataControllersUtil.ADMIN_NAME;

class AuthenticationTest extends AbstractTest {
    @Autowired
    JWTUtil jwtUtil;

    @Test
    void createAuthenticationToken() throws Exception {
        MvcResult result = mockMvc.perform(post("/authenticate")
                        .contentType(APPLICATION_JSON)
                        .content(TestDataControllersUtil.AUTH_REQUEST_JSON_STRING)
                        .accept(MediaType.ALL_VALUE))
                .andDo(print())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        MatcherFactory.usingEqualsComparator(String.class).assertMatch(jwtUtil.extractUsername(getContent(result).split("\"")[3]),ADMIN_NAME);
    }

}