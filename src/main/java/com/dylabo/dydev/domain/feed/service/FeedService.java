package com.dylabo.dydev.domain.feed.service;

import com.dylabo.dydev.domain.feed.service.dto.FeedRequestDto;
import com.dylabo.dydev.domain.feed.service.dto.FeedResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FeedService {

    List<FeedResponseDto> getRecentFeedList();

    FeedResponseDto setInsertFeed(FeedRequestDto feedRequestDto, List<MultipartFile> uploadFiles);

    Long setDeleteFeed(Long id);

}
