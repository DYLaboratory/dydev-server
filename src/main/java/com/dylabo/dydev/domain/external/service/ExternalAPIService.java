package com.dylabo.dydev.domain.external.service;

import com.dylabo.dydev.domain.external.enums.CityTypes;
import com.dylabo.dydev.domain.external.service.dto.WeatherDto;

import java.io.IOException;

public interface ExternalAPIService {

    WeatherDto getWeatherData(CityTypes city) throws IOException;

    String getDustData(CityTypes city) throws IOException;

}
