package com.dylabo.dydev.domain.website.enums;

import com.dylabo.core.common.annotation.EnumFindable;
import com.dylabo.core.common.enums.EnumMapperType;

@EnumFindable
public enum WebSiteTypes implements EnumMapperType {

    DEVELOP("develop"),
    REFERENCE("reference"),
    USEFUL("useful"),
    ENTERTAIN("entertain"),
    ETC("etc");

    private String value;

    WebSiteTypes(String value) {
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
