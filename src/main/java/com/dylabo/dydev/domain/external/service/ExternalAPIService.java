package com.dylabo.dydev.domain.external.service;

import com.dylabo.dydev.domain.external.service.dto.WeatherDto;

public interface ExternalAPIService {

    WeatherDto getOpenWeather(String city);

}
