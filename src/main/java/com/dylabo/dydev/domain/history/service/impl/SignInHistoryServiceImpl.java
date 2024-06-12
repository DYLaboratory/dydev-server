package com.dylabo.dydev.domain.history.service.impl;

import com.dylabo.core.common.utils.CommonRequestUtils;
import com.dylabo.dydev.domain.history.entity.SignInHistory;
import com.dylabo.dydev.domain.history.repository.SignInHistoryRepository;
import com.dylabo.dydev.domain.history.service.SignInHistoryService;
import com.dylabo.dydev.domain.history.service.dto.SignInHistoryRequestDto;
import com.dylabo.dydev.domain.history.service.dto.SignInHistoryResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignInHistoryServiceImpl implements SignInHistoryService {

    private final SignInHistoryRepository signInHistoryRepository;

    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public SignInHistoryResponseDto setInsertSignInHistory(SignInHistoryRequestDto requestDto) {
        SignInHistory signInHistory = SignInHistory.builder()
                .accessIp(requestDto.getAccessIp())
                .history(requestDto.getHistory())
                .build();

        signInHistory = signInHistoryRepository.save(signInHistory);

        return modelMapper.map(signInHistory, SignInHistoryResponseDto.class);
    }

}
