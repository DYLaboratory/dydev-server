package com.dylabo.dydev.domain.website.controller;

import com.dylabo.dydev.common.constants.CommonApiUrls;
import com.dylabo.dydev.domain.website.service.WebSiteService;
import com.dylabo.dydev.domain.website.service.dto.WebSiteRequestDto;
import com.dylabo.dydev.domain.website.service.dto.WebSiteResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(CommonApiUrls.API_PACKAGE_PREFIX_ADMIN + CommonApiUrls.API_PACKAGE_WEB_SITE)
public class AdminWebSiteController {

    private final WebSiteService webSiteService;

    @PostMapping("")
    public ResponseEntity<WebSiteResponseDto> doSetInsertWebSite(
            @Validated @RequestBody WebSiteRequestDto webSiteRequestDto,
            BindingResult bindingResult) {
        return new ResponseEntity<>(webSiteService.setInsertWebSite(webSiteRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WebSiteResponseDto> doSetUpdateWebSite(
            @PathVariable("id") Long id,
            @Validated @RequestBody WebSiteRequestDto webSiteRequestDto,
            BindingResult bindingResult) {
        return new ResponseEntity<>(webSiteService.setUpdateWebSite(id, webSiteRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> doSetDeleteWebSiteById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(webSiteService.setDeleteWebSiteById(id), HttpStatus.OK);
    }

}
