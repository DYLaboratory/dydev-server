package com.dylabo.dydev.domain.history.service.dto;

import lombok.Getter;

@Getter
public class LoginHistoryResponseDto extends LoginHistoryDto {

    private Long id;

    private String historyMessage;

    public String getHistoryMessage() {
        return this.getHistory().getValue();
    }
}
