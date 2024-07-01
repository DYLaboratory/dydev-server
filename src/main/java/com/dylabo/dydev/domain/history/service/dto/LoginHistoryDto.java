package com.dylabo.dydev.domain.history.service.dto;

import com.dylabo.core.domain.base.dto.BaseCDto;
import com.dylabo.dydev.domain.history.enums.LoginHistoryMessage;
import lombok.Getter;

@Getter
public class LoginHistoryDto extends BaseCDto {

    private String accessIp;

    private LoginHistoryMessage history;

}
