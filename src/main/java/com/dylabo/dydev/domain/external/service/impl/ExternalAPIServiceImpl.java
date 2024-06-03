package com.dylabo.dydev.domain.external.service.impl;

import com.dylabo.core.common.components.ExternalAPIComponent;
import com.dylabo.dydev.common.properties.ExternalProperties;
import com.dylabo.dydev.domain.external.service.ExternalAPIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExternalAPIServiceImpl implements ExternalAPIService {

    private final ExternalAPIComponent externalAPIComponent;

    private final ExternalProperties externalProperties;

    @Override
    public String getOpenWeather(String city) {
        String baseUrl = externalProperties.getOpenWeatherUrl();

        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("q", city)
                .queryParam("appid", externalProperties.getOpenWeatherApiKey())
                .queryParam("units", "metric")
                .toUriString();

        return externalAPIComponent.callExternalApi(url);
    }

}
