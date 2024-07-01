package com.dylabo.dydev.domain.history.controller;

import com.dylabo.dydev.common.constants.CommonApiUrls;
import com.dylabo.dydev.domain.history.service.LoginHistoryService;
import com.dylabo.dydev.domain.history.service.dto.LoginHistoryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(CommonApiUrls.API_PACKAGE_PREFIX_COMMON + CommonApiUrls.API_PACKAGE_LOGIN_HISTORY)
public class LoginHistoryController {

    private final LoginHistoryService loginHistoryService;

    @GetMapping("/list")
    public ResponseEntity<Page<LoginHistoryResponseDto>> doGetLoginHistoryListByLoginUser(Pageable pageable) {
        return new ResponseEntity<>(loginHistoryService.getLoginHistoryListByLoginUser(pageable), HttpStatus.OK);
    }

}
