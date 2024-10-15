package com.dylabo.dydev.domain.feed.controller;

import com.dylabo.dydev.common.constants.CommonApiUrls;
import com.dylabo.dydev.domain.feed.service.FeedService;
import com.dylabo.dydev.domain.feed.service.dto.FeedResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(CommonApiUrls.API_PACKAGE_PREFIX_COMMON + CommonApiUrls.API_PACKAGE_FEED)
public class FeedController {

    private final FeedService feedService;

    @GetMapping("/list")
    public ResponseEntity<List<FeedResponseDto>> doGetRecentFeedList() {
        return new ResponseEntity<>(feedService.getRecentFeedList(), HttpStatus.OK);
    }

}
