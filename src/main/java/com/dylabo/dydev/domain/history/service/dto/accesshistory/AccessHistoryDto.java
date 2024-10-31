package com.dylabo.dydev.domain.history.service.dto.accesshistory;

import com.dylabo.core.domain.base.dto.BaseCDto;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AccessHistoryDto extends BaseCDto {

    private String accessIp;

    @NotNull
    private String url;

    public void setAccessIp(String accessIp) {
        this.accessIp = accessIp;
    }
}
