package com.dylabo.dydev.domain.website.controller;

import com.dylabo.dydev.common.constants.CommonApiUrls;
import com.dylabo.dydev.domain.website.service.WebSiteService;
import com.dylabo.dydev.domain.website.service.dto.WebSiteResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(CommonApiUrls.API_PACKAGE_WEB_SITE)
public class WebSiteController {

    private final WebSiteService webSiteService;

    @GetMapping("/{id}")
    public ResponseEntity<WebSiteResponseDto> doGetWebSiteById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(webSiteService.getWebSiteById(id), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<WebSiteResponseDto>> doGetWebSiteList() {
        return new ResponseEntity<>(webSiteService.getWebSiteList(), HttpStatus.OK);
    }

}
