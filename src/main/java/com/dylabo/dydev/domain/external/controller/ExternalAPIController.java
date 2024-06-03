package com.dylabo.dydev.domain.external.controller;

import com.dylabo.dydev.domain.external.service.ExternalAPIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/external")
public class ExternalAPIController {

    private final ExternalAPIService externalAPIService;

    @GetMapping("/weather")
    public ResponseEntity<String> doGetOpenWeather(@RequestParam("city") String city) {
        return new ResponseEntity<>(externalAPIService.getOpenWeather(city), HttpStatus.OK);
    }

}
