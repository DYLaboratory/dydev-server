package com.dylabo.dydev.domain.system.service.dto;

import com.dylabo.core.domain.base.dto.BaseCUDto;
import com.dylabo.dydev.common.enums.SystemLanguageTypes;
import com.dylabo.dydev.common.enums.SystemThemeTypes;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SystemSettingsDto extends BaseCUDto {

    @NotNull
    @Size(max = 10)
    private String version;

    @NotNull
    private SystemThemeTypes defaultTheme;

    @NotNull
    private SystemLanguageTypes defaultLang;

    @NotNull
    @Size(max = 10)
    private Integer feedDays;

}
