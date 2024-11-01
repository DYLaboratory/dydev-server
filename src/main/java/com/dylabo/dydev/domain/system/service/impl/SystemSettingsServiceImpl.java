package com.dylabo.dydev.domain.system.service.impl;

import com.dylabo.dydev.common.enums.SystemLanguageTypes;
import com.dylabo.dydev.common.enums.SystemThemeTypes;
import com.dylabo.dydev.domain.system.domain.SystemSettings;
import com.dylabo.dydev.domain.system.repository.SystemSettingsRepository;
import com.dylabo.dydev.domain.system.service.SystemSettingsService;
import com.dylabo.dydev.domain.system.service.dto.SystemSettingsRequestDto;
import com.dylabo.dydev.domain.system.service.dto.SystemSettingsResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemSettingsServiceImpl implements SystemSettingsService {

    private final SystemSettingsRepository systemSettingsRepository;

    private final ModelMapper modelMapper;

    private static final SystemSettings INITIAL_SYSTEM_SETTINGS = SystemSettings.builder()
            .version("1.0")
            .defaultTheme(SystemThemeTypes.WHITE)
            .defaultLang(SystemLanguageTypes.EN)
            .feedDays(3)
            .build();

    /**
     * 최근 1개 데이터를 가져오고 없으면 초기값으로 생성한다.
     * (무조건 값 1개는 있도록)
     *
     * @return
     */
    @Override
    @Transactional
    public SystemSettingsResponseDto getTopSystemSettings() {
        Optional<SystemSettings> systemSettingsOp = systemSettingsRepository.findTopByOrderByCreateDateTimeDesc();

        // 값 없으면 최초 데이터 삽입
        SystemSettings systemSettings = systemSettingsOp
                .orElse(systemSettingsRepository.save(INITIAL_SYSTEM_SETTINGS));

        return modelMapper.map(systemSettings, SystemSettingsResponseDto.class);
    }

    /**
     * update없이 변경사항 발생할 때마다 새로 생성한다.
     * (변경사항 관리 위해서)
     *
     * @param systemSettingsRequestDto
     * @return
     */
    @Override
    @Transactional
    public SystemSettingsResponseDto setInsertSystemSettings(SystemSettingsRequestDto systemSettingsRequestDto) {
        SystemSettings systemSettings = modelMapper.map(systemSettingsRequestDto, SystemSettings.class);

        systemSettings = systemSettingsRepository.save(systemSettings);

        return modelMapper.map(systemSettings, SystemSettingsResponseDto.class);
    }

}
