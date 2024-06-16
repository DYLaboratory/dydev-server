package com.dylabo.dydev.domain.website.service.impl;

import com.dylabo.core.common.exception.ApiException;
import com.dylabo.dydev.common.exception.DydevErrorMessage;
import com.dylabo.dydev.domain.website.entity.WebSite;
import com.dylabo.dydev.domain.website.repository.WebSiteRepository;
import com.dylabo.dydev.domain.website.service.WebSiteService;
import com.dylabo.dydev.domain.website.service.dto.WebSiteRequestDto;
import com.dylabo.dydev.domain.website.service.dto.WebSiteResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class WebSiteServiceImpl implements WebSiteService {

    private final WebSiteRepository webSiteRepository;

    private final ModelMapper modelMapper;

    private WebSite getWebSiteEntityById(Long id) {
        return webSiteRepository.findById(id)
                .orElseThrow(() -> new ApiException(DydevErrorMessage.WEB_SITE_NOT_FOUND));
    }

    @Override
    public WebSiteResponseDto getWebSiteById(Long id) {
        return modelMapper.map(getWebSiteEntityById(id), WebSiteResponseDto.class);
    }

    @Override
    public List<WebSiteResponseDto> getWebSiteList() {
        List<WebSite> webSiteList = webSiteRepository.findAllBySort();

        return webSiteList.stream()
                .map(webSite -> modelMapper.map(webSite, WebSiteResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public WebSiteResponseDto setInsertWebSite(WebSiteRequestDto webSiteRequestDto) {
        WebSite webSite = webSiteRepository.save(
                modelMapper.map(webSiteRequestDto, WebSite.class)
        );

        return modelMapper.map(webSite, WebSiteResponseDto.class);
    }

    @Override
    @Transactional
    public WebSiteResponseDto setUpdateWebSite(Long id, WebSiteRequestDto webSiteRequestDto) {
        WebSite webSite = getWebSiteEntityById(id);

        modelMapper.map(webSiteRequestDto, webSite);

        return modelMapper.map(webSite, WebSiteResponseDto.class);
    }

    @Override
    @Transactional
    public Long setDeleteWebSiteById(Long id) {
        webSiteRepository.deleteById(id);

        return id;
    }

    @Override
    @Transactional
    public List<Long> setDeleteWebSiteByIdList(List<Long> idList) {
        webSiteRepository.deleteByIdIn(idList);

        return idList;
    }
}
