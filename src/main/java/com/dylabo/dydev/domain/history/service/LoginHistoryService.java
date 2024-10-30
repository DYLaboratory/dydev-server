package com.dylabo.dydev.domain.history.service;

import com.dylabo.dydev.domain.history.enums.LoginHistoryMessage;
import com.dylabo.dydev.domain.history.service.dto.loginhistory.LoginHistoryResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoginHistoryService {

    Page<LoginHistoryResponseDto> getLoginHistoryListByLoginUser(Pageable pageable);

    void setInsertLoginHistory(String userId, LoginHistoryMessage history);

}
