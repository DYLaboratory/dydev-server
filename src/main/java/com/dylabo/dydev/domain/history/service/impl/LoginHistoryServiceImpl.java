package com.dylabo.dydev.domain.history.service.impl;

import com.dylabo.core.common.exception.ApiException;
import com.dylabo.core.common.exception.message.ErrorMessage;
import com.dylabo.core.common.utils.CommonObjectUtils;
import com.dylabo.core.common.utils.CommonRequestUtils;
import com.dylabo.dydev.common.session.SessionComponent;
import com.dylabo.dydev.common.session.SessionDto;
import com.dylabo.dydev.domain.history.entity.LoginHistory;
import com.dylabo.dydev.domain.history.enums.LoginHistoryMessage;
import com.dylabo.dydev.domain.history.repository.LoginHistoryRepository;
import com.dylabo.dydev.domain.history.service.LoginHistoryService;
import com.dylabo.dydev.domain.history.service.dto.loginhistory.LoginHistoryResponseDto;
import com.dylabo.dydev.domain.user.entity.User;
import com.dylabo.dydev.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoginHistoryServiceImpl implements LoginHistoryService {

    private final LoginHistoryRepository loginHistoryRepository;

    private final UserService userService;

    private final SessionComponent sessionComponent;

    private final ModelMapper modelMapper;

    @Override
    public Page<LoginHistoryResponseDto> getLoginHistoryListByLoginUser(Pageable pageable) {
        SessionDto session = sessionComponent.getSession();

        if (CommonObjectUtils.nonNull(session)) {
            Page<LoginHistory> loginHistoryPage = loginHistoryRepository.findLoginHistoriesByUserIdAndPaging(session.getUserId(), pageable);

            return new PageImpl<>(loginHistoryPage.stream()
                    .map(lh -> modelMapper.map(lh, LoginHistoryResponseDto.class))
                    .collect(Collectors.toList()),
                    loginHistoryPage.getPageable(),
                    loginHistoryPage.getTotalElements()
            );
        } else {
            throw new ApiException(ErrorMessage.ACCESS_DENIED);
        }
    }

    @Override
    @Transactional
    public void setInsertLoginHistory(String userId, LoginHistoryMessage history) {
        if (CommonObjectUtils.isNull(history)) {
            return;
        }

        User user = userService.getUserEntityByUserId(userId)
                .orElseThrow(() -> new ApiException(ErrorMessage.USER_NOT_FOUND));

        LoginHistory loginHistory = LoginHistory.builder()
                .user(user)
                .accessIp(CommonRequestUtils.getClientIp())
                .history(history)
                .build();

        loginHistoryRepository.save(loginHistory);
    }

}
