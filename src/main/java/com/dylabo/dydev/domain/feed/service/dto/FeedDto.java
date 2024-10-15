package com.dylabo.dydev.domain.feed.service.dto;

import com.dylabo.core.domain.base.dto.BaseCUDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class FeedDto extends BaseCUDto {

    @NotNull
    @Size(max = 50)
    private String title;

    @NotNull
    @Size(max = 1000)
    private String content;

    @Size(max = 30)
    private String place;

    @Size(max = 200)
    private String link;

    @Size(max = 200)
    private String tags;

    private Integer viewCount;

}
