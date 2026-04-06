package com.htam25.minishop.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.htam25.minishop.dto.response.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;


@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException ex) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        ApiErrorResponse error = new ApiErrorResponse(
                401,
                "Unauthorized",
                "Unauthorized",
                LocalDateTime.now()
        );

        new ObjectMapper().writeValue(response.getOutputStream(), error);
    }
}