package com.dylabo.dydev.common.enums;

import com.dylabo.core.common.annotation.EnumFindable;
import com.dylabo.core.common.enums.EnumMapperType;

@EnumFindable
public enum SystemLanguageTypes implements EnumMapperType {
    KR("Korean"),
    EN("English");

    SystemLanguageTypes(String value) {
        this.value = value;
    }

    private final String value;

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }
}
