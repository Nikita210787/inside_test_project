package ru.Inside_test_project.security;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

@Setter
@Getter

public class AuthRequest {
    private String name;
    private String password;
}
