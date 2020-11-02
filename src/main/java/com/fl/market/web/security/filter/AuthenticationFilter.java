package com.fl.market.web.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fl.market.domain.dto.UserDTO;
import com.fl.market.web.response.ResponseController;
import com.fl.market.web.security.JWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private JWTUtil jwtUtil;

    private ObjectMapper mapper;

    public AuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, ObjectMapper mapper) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.mapper = mapper;

        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/token", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String email = obtainUsername(request) == null ? "" : obtainUsername(request).trim();
        String password = obtainPassword(request) == null ? "" : obtainPassword(request);

        System.out.println(email + " - " + password);

        try {
            UserDTO credenciales = mapper.readValue(request.getInputStream(), UserDTO.class);
            email = credenciales.getEmail();
            password = credenciales.getPassword();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(email + " - " + password);

        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password, new ArrayList<>()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        var jwt = jwtUtil.generateToken((User) authResult.getPrincipal());
        var body = new ResponseController("Autenticado", jwt);

        response.getWriter().write(mapper.writeValueAsString(body));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
       var body = new ResponseController("Error de credendiacles", null);

        response.getWriter().write(mapper.writeValueAsString(body));
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");
    }

}
