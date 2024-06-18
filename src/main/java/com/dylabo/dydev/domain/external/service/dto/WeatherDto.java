package com.dylabo.dydev.domain.external.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WeatherDto {
    private String present;
    private String forecast;
    private String dust;
}
