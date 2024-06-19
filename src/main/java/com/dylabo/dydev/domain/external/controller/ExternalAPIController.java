package com.dylabo.dydev.domain.external.controller;

import com.dylabo.dydev.common.constants.CommonApiUrls;
import com.dylabo.dydev.domain.external.enums.CityTypes;
import com.dylabo.dydev.domain.external.service.ExternalAPIService;
import com.dylabo.dydev.domain.external.service.dto.WeatherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping(CommonApiUrls.API_PACKAGE_PREFIX_COMMON + CommonApiUrls.API_PACKAGE_EXTERNAL)
public class ExternalAPIController {

    private final ExternalAPIService externalAPIService;

    @GetMapping("/weather-dust")
    public ResponseEntity<WeatherDto> doGetOpenWeather(
            @RequestParam("city") CityTypes city,
            @RequestParam("weather") Boolean weather,
            @RequestParam("dust") Boolean dust) throws IOException {
        return new ResponseEntity<>(externalAPIService.getWeatherDustData(city, weather, dust), HttpStatus.OK);
    }

}
