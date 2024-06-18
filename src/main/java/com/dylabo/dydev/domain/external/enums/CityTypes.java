package com.dylabo.dydev.domain.external.enums;

import com.dylabo.core.common.annotation.EnumFindable;
import com.dylabo.core.common.enums.EnumMapperType;

@EnumFindable
public enum CityTypes implements EnumMapperType {
    Seoul("서울"),
    Daegu("대구");

    private String value;

    CityTypes(String value) {
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
