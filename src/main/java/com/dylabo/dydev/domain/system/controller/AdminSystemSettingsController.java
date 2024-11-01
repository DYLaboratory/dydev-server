package com.dylabo.dydev.domain.system.controller;

import com.dylabo.dydev.common.constants.CommonApiUrls;
import com.dylabo.dydev.domain.system.service.SystemSettingsService;
import com.dylabo.dydev.domain.system.service.dto.SystemSettingsRequestDto;
import com.dylabo.dydev.domain.system.service.dto.SystemSettingsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CommonApiUrls.API_PACKAGE_PREFIX_SYSTEM + CommonApiUrls.API_PACKAGE_SETTINGS)
@RequiredArgsConstructor
public class AdminSystemSettingsController {

    private final SystemSettingsService systemSettingsService;

    @PostMapping("")
    public ResponseEntity<SystemSettingsResponseDto> doSetInsertSystemSettings(
            @Validated @RequestBody SystemSettingsRequestDto systemSettingsRequestDto,
            BindingResult bindingResult) {
        return new ResponseEntity<>(systemSettingsService.setInsertSystemSettings(systemSettingsRequestDto), HttpStatus.CREATED);
    }

}
