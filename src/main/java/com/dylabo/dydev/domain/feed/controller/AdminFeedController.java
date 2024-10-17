package com.dylabo.dydev.domain.feed.controller;

import com.dylabo.dydev.common.constants.CommonApiUrls;
import com.dylabo.dydev.domain.feed.service.FeedService;
import com.dylabo.dydev.domain.feed.service.dto.FeedRequestDto;
import com.dylabo.dydev.domain.feed.service.dto.FeedResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(CommonApiUrls.API_PACKAGE_PREFIX_ADMIN + CommonApiUrls.API_PACKAGE_FEED)
public class AdminFeedController {

    private final FeedService feedService;

    @PostMapping("")
    public ResponseEntity<FeedResponseDto> doSetInsertFeed(
            @Validated @RequestPart(name = "feed", required = true) FeedRequestDto feedRequestDto,
            @RequestPart(name = "uploadFiles") List<MultipartFile> uploadFiles,
            BindingResult bindingResult) {
        return new ResponseEntity<>(feedService.setInsertFeed(feedRequestDto, uploadFiles), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> doSetDeleteFeed(@PathVariable("id") Long id) {
        return new ResponseEntity<>(feedService.setDeleteFeed(id), HttpStatus.OK);
    }

}
