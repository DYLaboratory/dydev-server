package com.dylabo.dydev.domain.history.service;

import com.dylabo.dydev.domain.history.service.dto.SignInHistoryRequestDto;
import com.dylabo.dydev.domain.history.service.dto.SignInHistoryResponseDto;

public interface SignInHistoryService {

    SignInHistoryResponseDto setInsertSignInHistory(SignInHistoryRequestDto requestDto);

}
