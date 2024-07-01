package com.dylabo.dydev.domain.history.enums;

import com.dylabo.core.common.annotation.EnumFindable;
import com.dylabo.core.common.enums.EnumMapperType;

@EnumFindable
public enum LoginHistoryMessage implements EnumMapperType {
    LOGIN_SUCCESS("로그인 성공"),
    LOGIN_PASSWORD_FAIL("비밀번호 불일치");

    private String value;

    LoginHistoryMessage(String value) {
        this.value = value;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }
}
