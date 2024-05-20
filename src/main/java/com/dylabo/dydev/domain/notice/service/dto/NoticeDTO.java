package com.dylabo.dydev.domain.notice.service.dto;

import com.dylabo.core.domain.base.dto.BaseCUDto;
import com.dylabo.dydev.domain.notice.enums.NoticeTypes;
import lombok.Getter;

@Getter
public class NoticeDTO extends BaseCUDto {

    private NoticeTypes noticeType;

    private String title;

    private String content;

    private Integer viewCount;

}
