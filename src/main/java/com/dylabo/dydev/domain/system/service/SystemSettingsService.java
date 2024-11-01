package com.dylabo.dydev.domain.system.service;

import com.dylabo.dydev.domain.system.service.dto.SystemSettingsRequestDto;
import com.dylabo.dydev.domain.system.service.dto.SystemSettingsResponseDto;

public interface SystemSettingsService {

    SystemSettingsResponseDto getTopSystemSettings();

    SystemSettingsResponseDto setInsertSystemSettings(SystemSettingsRequestDto systemSettingsRequestDto);

}
