package com.dylabo.dydev.domain.file.enums;

import com.dylabo.core.common.annotation.EnumFindable;
import com.dylabo.core.common.enums.EnumMapperType;
import com.dylabo.dydev.common.components.AWSS3Component;

@EnumFindable
public enum FileTypes implements EnumMapperType {
    TEMP("TEMP"),       // 임시
    FEED("FEED"),       // 피드
    NOTICE("NOTICE"),   // 공지사항 파일
    NOTICE_EDT("NOTICE_EDT"),   // 공지사항 에디터
    BLOG("BLOG"),       // 블로그 파일
    BLOG_EDT("BLOG_EDT");   // 블로그 에디터

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
            case NOTICE -> AWSS3Component.S3_ROOT_FILE_PATH + "notice/";
            case NOTICE_EDT -> AWSS3Component.S3_ROOT_FILE_PATH + "notice_edt/";
            case BLOG -> AWSS3Component.S3_ROOT_FILE_PATH + "blog/";
            case BLOG_EDT -> AWSS3Component.S3_ROOT_FILE_PATH + "blog_edt/";
        };
    }

}
