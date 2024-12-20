package com.dylabo.dydev.domain.external.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WeatherDto {
    private String present;
    private String forecast;
    private String dust;
}
