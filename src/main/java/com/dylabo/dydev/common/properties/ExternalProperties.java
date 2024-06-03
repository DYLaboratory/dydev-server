package com.dylabo.dydev.common.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class ExternalProperties {

    @Value("${external.url.openweather}")
    private String openWeatherUrl;

    @Value("${external.api-key.openweather}")
    private String openWeatherApiKey;

}
