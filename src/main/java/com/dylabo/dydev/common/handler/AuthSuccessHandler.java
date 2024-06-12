package com.dylabo.dydev.common.handler;

import com.dylabo.dydev.common.session.SessionDto;
import com.dylabo.dydev.common.utils.SessionUtils;
import com.dylabo.dydev.domain.user.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();

        user.updateLastLoginDateTime();

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        // create and set session
        SessionDto sessionDto = SessionDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .lastLoginDateTime(user.getLastLoginDateTime().format(formatter))
                .build();

        SessionUtils.setSession(sessionDto);

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(sessionDto));
    }

}
