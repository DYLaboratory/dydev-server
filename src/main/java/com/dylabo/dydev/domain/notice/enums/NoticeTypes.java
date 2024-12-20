package com.dylabo.dydev.domain.notice.enums;

import com.dylabo.core.common.annotation.EnumFindable;
import com.dylabo.core.common.enums.EnumMapperType;

@EnumFindable
public enum NoticeTypes implements EnumMapperType {
    NOTICE("NOTICE"),
    VERSION("VERSION"),
    ETC("ETC");

    private final String value;

    NoticeTypes(String value) {
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
