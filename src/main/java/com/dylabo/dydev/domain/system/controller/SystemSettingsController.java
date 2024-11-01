package com.dylabo.dydev.domain.system.controller;

import com.dylabo.dydev.common.constants.CommonApiUrls;
import com.dylabo.dydev.domain.system.service.SystemSettingsService;
import com.dylabo.dydev.domain.system.service.dto.SystemSettingsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CommonApiUrls.API_PACKAGE_PREFIX_COMMON + CommonApiUrls.API_PACKAGE_SETTINGS)
@RequiredArgsConstructor
public class SystemSettingsController {

    private final SystemSettingsService systemSettingsService;

    @GetMapping("")
    public ResponseEntity<SystemSettingsResponseDto> doGetSystemSettings() {
        return new ResponseEntity<>(systemSettingsService.getTopSystemSettings(), HttpStatus.OK);
    }

}
