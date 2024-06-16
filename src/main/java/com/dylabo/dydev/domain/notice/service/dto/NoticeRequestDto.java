package com.dylabo.dydev.domain.notice.service.dto;

import com.dylabo.dydev.domain.notice.enums.NoticeTypes;
import lombok.Getter;
import lombok.Setter;

@Getter
public class NoticeRequestDto {

    private NoticeTypes noticeType;

    private String title;

    private String content;

    @Getter
    @Setter
    public static class Search {

    }

}
