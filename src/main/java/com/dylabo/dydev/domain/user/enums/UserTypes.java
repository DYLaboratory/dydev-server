package com.dylabo.dydev.domain.user.enums;

import com.dylabo.core.common.annotation.EnumFindable;
import com.dylabo.core.common.enums.EnumMapperType;

@EnumFindable
public enum UserTypes implements EnumMapperType {
    USER("USER"),
    ADMIN("ADMIN"),
    SUPER("SUPER");

    private String value;

    UserTypes(String value) {
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
