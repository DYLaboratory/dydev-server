package com.dylabo.dydev.domain.external.service.impl;

import com.dylabo.core.common.components.ExternalAPIComponent;
import com.dylabo.core.common.utils.ErrorLogUtils;
import com.dylabo.dydev.common.properties.ExternalProperties;
import com.dylabo.dydev.domain.external.enums.CityTypes;
import com.dylabo.dydev.domain.external.service.ExternalAPIService;
import com.dylabo.dydev.domain.external.service.dto.WeatherDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExternalAPIServiceImpl implements ExternalAPIService {

    private final ExternalAPIComponent externalAPIComponent;

    private final ExternalProperties externalProperties;

    @Override
    public WeatherDto getWeatherData(CityTypes city) throws IOException {
        String baseUrl = externalProperties.getOpenWeatherUrl();

        String presentUrl = UriComponentsBuilder.fromHttpUrl(baseUrl + "weather")
                .queryParam("q", city.getKey())
                .queryParam("appid", externalProperties.getOpenWeatherApiKey())
                .queryParam("units", "metric")
                .toUriString();

        String forecastUrl = UriComponentsBuilder.fromHttpUrl(baseUrl + "forecast")
                .queryParam("q", city.getKey())
                .queryParam("appid", externalProperties.getOpenWeatherApiKey())
                .queryParam("units", "metric")
                .toUriString();

        return WeatherDto.builder()
                .present(externalAPIComponent.callExternalApi(presentUrl))
                .forecast(externalAPIComponent.callExternalApi(forecastUrl))
                .dust(getDustData(city))
                .build();
    }

    @Override
    public String getDustData(CityTypes city) {
        String baseUrl = externalProperties.getDustUrl();

        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "getCtprvnRltmMesureDnsty")
                .queryParam("serviceKey", externalProperties.getDustApiKeyEncode())
//                .queryParam("sidoName", city.getValue())
                .queryParam("returnType", "json")
                .queryParam("ver", "1.1")
                .toUriString().concat("&sidoName=" + city.getValue());

        String result;

        try {
            result = externalAPIComponent.callExternalApi(url);

            if (result.startsWith("<")) {
                return null;
            }
        } catch (Exception e) {
            ErrorLogUtils.printError(log, e);
            return null;
        }

        return result;
    }

}
