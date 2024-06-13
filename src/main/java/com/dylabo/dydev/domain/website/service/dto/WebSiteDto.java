package com.dylabo.dydev.domain.website.service.dto;

import com.dylabo.core.domain.base.dto.BaseCUDto;
import com.dylabo.dydev.domain.website.enums.WebSiteTypes;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class WebSiteDto extends BaseCUDto {

    @NotEmpty
    private WebSiteTypes webSiteType;

    @NotEmpty
    @Size(min = 1, max = 30)
    private String name;

    @NotEmpty
    @Size(min = 1, max = 30)
    private String description;

    @NotEmpty
    @Size(min = 10, max = 200)
    private String url;

}
