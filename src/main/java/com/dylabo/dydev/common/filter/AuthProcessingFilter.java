package com.dylabo.dydev.common.filter;

import com.dylabo.core.common.utils.CommonRequestUtils;
import com.dylabo.core.common.utils.CommonStringUtils;
import com.dylabo.dydev.domain.auth.service.dto.AuthDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
public class AuthProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public AuthProcessingFilter() {
        super(new AntPathRequestMatcher("/auth/sign-in"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (!HttpMethod.POST.name().equals(request.getMethod())) {
            throw new IllegalStateException("Authentication is not supported");
        }

        AuthDto authDto = objectMapper.readValue(request.getReader(), AuthDto.class);

        String userId = authDto.getUserId();
        String password = authDto.getPassword();

        log.info("========== login attempt datetime {} ==========", LocalDateTime.now());
        log.info("========== login attempt id {} ==========", userId);
        log.info("========== login attempt ip {} ==========", CommonRequestUtils.getClientIp());

        // 값이 비었을 경우
        if (CommonStringUtils.nonHasText(userId)
                || CommonStringUtils.nonHasText(password)) {
            throw new IllegalArgumentException("username or password is empty");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, password);

        return getAuthenticationManager().authenticate(authenticationToken);
    }

}
