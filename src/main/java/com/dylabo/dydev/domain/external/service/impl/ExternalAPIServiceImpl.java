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
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExternalAPIServiceImpl implements ExternalAPIService {

    private final ExternalAPIComponent externalAPIComponent;

    private final ExternalProperties externalProperties;

    @Override
    public WeatherDto getWeatherDustData(CityTypes city, Boolean weather, Boolean dust) throws IOException {
        WeatherDto weatherDto = new WeatherDto();

        // 날씨
        if (weather) {
            weatherDto.setPresent(getPresentWeather(city));
            weatherDto.setForecast(getForecastWeather(city));
        }

        // 미세먼지
        if (dust) {
            weatherDto.setDust(getDustData(city));
        }

        return weatherDto;
    }

    @Override
    public String getPresentWeather(CityTypes city) throws IOException {
        String baseUrl = externalProperties.getOpenWeatherUrl();

        String presentUrl = UriComponentsBuilder.fromHttpUrl(baseUrl + "weather")
                .queryParam("q", city.getKey())
                .queryParam("appid", externalProperties.getOpenWeatherApiKey())
                .queryParam("units", "metric")
                .toUriString();

        return externalAPIComponent.callExternalApi(presentUrl);
    }

    @Override
    public String getForecastWeather(CityTypes city) throws IOException {
        String baseUrl = externalProperties.getOpenWeatherUrl();

        String forecastUrl = UriComponentsBuilder.fromHttpUrl(baseUrl + "forecast")
                .queryParam("q", city.getKey())
                .queryParam("appid", externalProperties.getOpenWeatherApiKey())
                .queryParam("units", "metric")
                .toUriString();

        return externalAPIComponent.callExternalApi(forecastUrl);
    }

    @Override
    public String getDustData(CityTypes city) {
        String baseUrl = externalProperties.getDustUrl();

        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "getCtprvnRltmMesureDnsty")
                .encode(StandardCharsets.UTF_8)
                .queryParam("serviceKey", externalProperties.getDustApiKeyEncode())
                .queryParam("sidoName", city.getValue())
//                .queryParam("sidoName", city.getValue())
                .queryParam("returnType", "json")
                .queryParam("ver", "1.1")
                .toUriString();

        String result;

        try {
            // 요청
            result = externalAPIComponent.callExternalApi(url);

            // 실패하면 한번 더 요청
            if (result.startsWith("<")) {
                result = externalAPIComponent.callExternalApi(url);
            } else {
                return result;
            }

            // service key decode로 재요청
            if (result.startsWith("<")) {
                url = UriComponentsBuilder.fromHttpUrl(baseUrl + "getCtprvnRltmMesureDnsty")
                        .queryParam("serviceKey", externalProperties.getDustApiKeyDecode())
                        .queryParam("sidoName", city.getValue())
                        .queryParam("returnType", "json")
                        .queryParam("ver", "1.1")
                        .toUriString();

                result = externalAPIComponent.callExternalApi(url);
            } else {
                return result;
            }

            // 실패하면 한번 더 요청
            if (result.startsWith("<")) {
                result = externalAPIComponent.callExternalApi(url);
            } else {
                return result;
            }

            // 실패
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
