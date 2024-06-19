package com.dylabo.dydev.domain.external.service;

import com.dylabo.dydev.domain.external.enums.CityTypes;
import com.dylabo.dydev.domain.external.service.dto.WeatherDto;

import java.io.IOException;

public interface ExternalAPIService {

    WeatherDto getWeatherDustData(CityTypes city, Boolean weather, Boolean dust) throws IOException;

    String getPresentWeather(CityTypes city) throws IOException;

    String getForecastWeather(CityTypes city) throws IOException;

    String getDustData(CityTypes city) throws IOException;

}
