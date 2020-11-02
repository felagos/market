package com.fl.market.integration;

import com.fl.market.web.security.JWTUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;

public class BaseIntegration {

    @Autowired
    private JWTUtil jwtUtil;

    protected String TOKEN;

    @BeforeEach
    public void generateToken() {
        var user = new User("demo@gmail.com", "123456", new ArrayList<>());

        TOKEN = jwtUtil.generateToken(user);
    }

}
