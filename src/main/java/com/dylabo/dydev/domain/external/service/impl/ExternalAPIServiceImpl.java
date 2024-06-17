package com.dylabo.dydev.domain.external.service.impl;

import com.dylabo.core.common.components.ExternalAPIComponent;
import com.dylabo.dydev.common.properties.ExternalProperties;
import com.dylabo.dydev.domain.external.service.ExternalAPIService;
import com.dylabo.dydev.domain.external.service.dto.WeatherDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExternalAPIServiceImpl implements ExternalAPIService {

    private final ExternalAPIComponent externalAPIComponent;

    private final ExternalProperties externalProperties;

    @Override
    public WeatherDto getOpenWeather(String city) {
        String baseUrl = externalProperties.getOpenWeatherUrl();

        String presentUrl = UriComponentsBuilder.fromHttpUrl(baseUrl + "weather")
                .queryParam("q", city)
                .queryParam("appid", externalProperties.getOpenWeatherApiKey())
                .queryParam("units", "metric")
                .toUriString();

        String forecastUrl = UriComponentsBuilder.fromHttpUrl(baseUrl + "forecast")
                .queryParam("q", city)
                .queryParam("appid", externalProperties.getOpenWeatherApiKey())
                .queryParam("units", "metric")
                .toUriString();

        return WeatherDto.builder()
                .present(externalAPIComponent.callExternalApi(presentUrl))
                .forecast(externalAPIComponent.callExternalApi(forecastUrl))
                .build();
    }

}
