package com.dylabo.dydev.domain.website.service;

import com.dylabo.dydev.domain.website.service.dto.WebSiteRequestDto;
import com.dylabo.dydev.domain.website.service.dto.WebSiteResponseDto;

import java.util.List;

public interface WebSiteService {

    WebSiteResponseDto getWebSiteById(Long id);

    List<WebSiteResponseDto> getWebSiteList();

    WebSiteResponseDto setInsertWebSite(WebSiteRequestDto webSiteRequestDto);

    WebSiteResponseDto setUpdateWebSite(Long id, WebSiteRequestDto webSiteRequestDto);

    Long setDeleteWebSiteById(Long id);

    List<Long> setDeleteWebSiteByIdList(List<Long> idList);

}
