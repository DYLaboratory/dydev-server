package com.dylabo.dydev.domain.history.service.dto;

import com.dylabo.core.domain.base.dto.BaseCDto;
import lombok.Getter;

@Getter
public class SignInHistoryDto extends BaseCDto {

    private String accessIp;

    private String history;

}
