package com.dylabo.dydev.domain.history.service.dto.accesshistory;

import com.dylabo.core.domain.base.dto.BaseCDto;
import lombok.Getter;

@Getter
public class AccessHistoryDto extends BaseCDto {

    private String accessIp;

    public void setAccessIp(String accessIp) {
        this.accessIp = accessIp;
    }
}
