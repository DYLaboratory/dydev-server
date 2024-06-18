package com.dylabo.dydev.common.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class ExternalProperties {

    // open weather
    @Value("${external.openweather.url}")
    private String openWeatherUrl;

    @Value("${external.openweather.api-key}")
    private String openWeatherApiKey;

    // dust
    @Value("${external.dust.url}")
    private String dustUrl;

    @Value("${external.dust.api-key-encode}")
    private String dustApiKeyEncode;

    @Value("${external.dust.api-key-decode}")
    private String dustApiKeyDecode;

}
