package com.dylabo.dydev.domain.file.enums;

import com.dylabo.core.common.annotation.EnumFindable;
import com.dylabo.core.common.enums.EnumMapperType;
import com.dylabo.dydev.common.components.AWSS3Component;

@EnumFindable
public enum FileTypes implements EnumMapperType {
    TEMP("TEMP"),
    FEED("FEED"),
    EDITOR("EDITOR");

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
            case TEMP -> AWSS3Component.S3_ROOT_FILE_PATH + "temp/";
            case FEED -> AWSS3Component.S3_ROOT_FILE_PATH + "feed/";
            case EDITOR -> AWSS3Component.S3_ROOT_FILE_PATH + "editor/";
        };
    }

}
