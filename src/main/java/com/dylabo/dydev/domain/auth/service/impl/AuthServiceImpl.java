package com.dylabo.dydev.domain.auth.service.impl;

import com.dylabo.core.common.exception.ApiException;
import com.dylabo.core.common.exception.message.ErrorMessage;
import com.dylabo.core.common.utils.CommonRequestUtils;
import com.dylabo.core.common.utils.CommonStringUtils;
import com.dylabo.dydev.common.session.SessionComponent;
import com.dylabo.dydev.common.session.SessionDto;
import com.dylabo.dydev.domain.auth.service.AuthService;
import com.dylabo.dydev.domain.auth.service.dto.AuthDto;
import com.dylabo.dydev.domain.history.enums.LoginHistoryMessage;
import com.dylabo.dydev.domain.history.service.LoginHistoryService;
import com.dylabo.dydev.domain.user.entity.User;
import com.dylabo.dydev.domain.user.enums.UserTypes;
import com.dylabo.dydev.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    private final LoginHistoryService loginHistoryService;

    private final SessionComponent sessionComponent;

    private final PasswordEncoder passwordEncoder;

    @Override
    public SessionDto signIn(AuthDto authDto) {
        String userId = authDto.getUserId();
        String password = authDto.getPassword();

        log.info("========== login attempt datetime {} ==========", LocalDateTime.now());
        log.info("========== login attempt id {} ==========", userId);
        log.info("========== login attempt ip {} ==========", CommonRequestUtils.getClientIp());

        // 값이 비었을 경우
        if (CommonStringUtils.nonHasText(userId)
                || CommonStringUtils.nonHasText(password)) {
            throw new IllegalArgumentException("userid or password is empty");
        }

        // 1. 유저 조회
        User user = userService.getUserEntityByUserId(userId)
                .orElseThrow(() -> new ApiException(ErrorMessage.INVALID_USER_ID_AND_PASSWORD));

        // 2. 비밀번호 확인
        if (!passwordEncoder.matches(password, user.getPassword())) {
            loginHistoryService.setInsertLoginHistory(userId, LoginHistoryMessage.LOGIN_PASSWORD_FAIL);
            throw new ApiException(ErrorMessage.INVALID_USER_ID_AND_PASSWORD);
        }

        // 여기까지 오면 로그인 성공

        // security 등록
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userId, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        // 세션 설정
        SessionDto sessionDto = SessionDto.builder()
                .isAdmin(user.getUserType() == UserTypes.ADMIN || user.getUserType() == UserTypes.SUPER)
                .userId(user.getUserId())
                .userType(user.getUserType())
                .name(user.getName())
                .email(user.getEmail())
                .lastLoginDateTime(user.getLastLoginDateTime())
                .build();

        sessionComponent.setSession(sessionDto);
        sessionComponent.setSessionAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

        // 마지막 로그인일시 업데이트
        user.successLogin();

        // 로그인 내역
        loginHistoryService.setInsertLoginHistory(userId, LoginHistoryMessage.LOGIN_SUCCESS);

        return sessionDto;
    }

    @Override
    public String signOut() {
        sessionComponent.clearSession();

        return "Sign Out";
    }

}
