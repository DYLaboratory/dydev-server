package com.dylabo.dydev.domain.file.enums;

import com.dylabo.core.common.annotation.EnumFindable;
import com.dylabo.core.common.enums.EnumMapperType;

@EnumFindable
public enum FileTypes implements EnumMapperType {
    FEED("피드"),
    EDITOR("에디터");

    private String value;

    FileTypes(String value) {
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

    public String getPath() {
        return switch (this) {
            case FEED -> "dylabo/feed/";
            case EDITOR -> "dylabo/editor/";
        };
    }

}
