package com.dylabo.dydev.domain.notice.service.dto;

import com.dylabo.core.domain.base.dto.BaseCUDto;
import com.dylabo.dydev.domain.notice.enums.NoticeTypes;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class NoticeDto extends BaseCUDto {

    @NotNull
    private NoticeTypes noticeType;

    @NotEmpty
    @Size(max = 30)
    private String title;

    @NotEmpty
    private String content;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer viewCount;

}
